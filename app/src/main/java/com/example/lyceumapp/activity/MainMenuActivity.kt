package com.example.lyceumapp.activity

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.lyceumapp.Const
import com.example.lyceumapp.R
import com.example.lyceumapp.databinding.ActivityMainMenuBinding
import com.example.lyceumapp.fragment.EventsFragment
import com.example.lyceumapp.fragment.MainFragment
import com.example.lyceumapp.fragment.ScheduleFragment
import com.example.lyceumapp.fragment.TeachersFragment
import com.example.lyceumapp.viewmodel.MainMenuViewModel
import com.example.lyceumapp.viewmodel.MainMenuViewModelFactory
import com.google.android.material.navigation.NavigationView
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener

class MainMenuActivity : AppCompatActivity(),
OnNavigationItemSelectedListener {
    private lateinit var viewModel: MainMenuViewModel
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_download)

        viewModel = ViewModelProvider(this, MainMenuViewModelFactory(application))[MainMenuViewModel::class.java]

        val amountAttemptsToConnect = intent.extras?.getInt(Const.INTENT_KEY_AMOUNT_ATTEMPTS_TO_CONNECT)
        if(amountAttemptsToConnect!=null) viewModel.amountAttemptsToConnect = amountAttemptsToConnect

        viewModel.liveDataLessonsForSubgroup.observe(this) { pairLessonsAndIsActual ->
            if (pairLessonsAndIsActual.first == null) {
                //something went wrong and we can't get lessons for the subgroup from the server. We start NoResponseActivity
                val intent = Intent(this, NoResponseActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                intent.putExtra(Const.INTENT_KEY_NO_RESPONSE_TYPE, Const.NoResponseType.GetLessons)
                intent.putExtra(Const.INTENT_KEY_AMOUNT_ATTEMPTS_TO_CONNECT, viewModel.amountAttemptsToConnect)
                startActivity(intent)
            } else {
                //there is we've finally got lessons. We can show main_menu layout!
                val binding = ActivityMainMenuBinding.inflate(layoutInflater)
                setContentView(binding.root)

                //here if lessons aren't actual, we need to show special textView banner about it
                val textWarningLessonsAreNotActual = findViewById<TextView>(R.id.textWarningLessonsAreNotActual)
                textWarningLessonsAreNotActual.visibility = if(pairLessonsAndIsActual.second) View.GONE
                else View.VISIBLE

                drawerLayout = binding.drawerLayoutMainMenu

                val toolbar = findViewById<Toolbar>(R.id.toolbarMainMenu)
                setSupportActionBar(toolbar)

                val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_drawer_open, R.string.nav_drawer_close)
                toggle.syncState()

                binding.navViewMainMenu.setNavigationItemSelectedListener(this)

                binding.navViewMainMenu.setCheckedItem(R.id.menuItemMain)
                supportFragmentManager.beginTransaction().replace(R.id.frameLayoutMainMenu, MainFragment())
                    .commit()
            }
        }
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val fragment = when(item.itemId) {
            R.id.menuItemMain -> MainFragment()
            R.id.menuItemSchedule -> ScheduleFragment()
            R.id.menuItemTeachers -> TeachersFragment()
            R.id.menuItemEvents -> EventsFragment()
            else -> {
                Log.e(Const.LOG_TAG_DRAWER_INCORRECT_MENU_ITEM_ID, "The id in onNavigationItemSelected is incorrect!")
                MainFragment()
            }
        }

        supportFragmentManager.beginTransaction().replace(R.id.frameLayoutMainMenu, fragment)
            .commit()

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

}