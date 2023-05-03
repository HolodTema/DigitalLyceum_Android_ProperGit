package com.example.lyceumapp.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import com.example.lyceumapp.const
import com.example.lyceumapp.R
import com.example.lyceumapp.databinding.ActivityMainMenuBinding
import com.example.lyceumapp.fragment.EventsFragment
import com.example.lyceumapp.fragment.MainFragment
import com.example.lyceumapp.fragment.ScheduleFragment
import com.example.lyceumapp.fragment.TeachersFragment
import com.example.lyceumapp.json.grades.GradeJson
import com.example.lyceumapp.json.schools.SchoolJson
import com.example.lyceumapp.json.subgroups.SubgroupJson
import com.example.lyceumapp.viewmodel.MainMenuViewModel
import com.example.lyceumapp.viewmodel.MainMenuViewModelFactory
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener

class MainMenuActivity : AppCompatActivity(),
OnNavigationItemSelectedListener {
    private lateinit var viewModel: MainMenuViewModel
    private lateinit var school: SchoolJson
    private lateinit var grade: GradeJson
    private lateinit var subgroup: SubgroupJson
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_download)

        school = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) intent.extras!!.getParcelable(const.INTENT_KEY_SCHOOL, SchoolJson::class.java)!!
        else intent.extras!!.getParcelable(const.INTENT_KEY_SCHOOL)!!

        grade = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) intent.extras!!.getParcelable(const.INTENT_KEY_GRADE, GradeJson::class.java)!!
        else intent.extras!!.getParcelable(const.INTENT_KEY_GRADE)!!

        subgroup = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) intent.extras!!.getParcelable(const.INTENT_KEY_SUBGROUP, SubgroupJson::class.java)!!
        else intent.extras!!.getParcelable(const.INTENT_KEY_SUBGROUP)!!

        viewModel = ViewModelProvider(this, MainMenuViewModelFactory(application, school, grade, subgroup))[MainMenuViewModel::class.java]

        val amountAttemptsToConnect = intent.extras?.getInt(const.INTENT_KEY_AMOUNT_ATTEMPTS_TO_CONNECT)
        if(amountAttemptsToConnect!=null) viewModel.amountAttemptsToConnect = amountAttemptsToConnect

        viewModel.liveDataLessonsForSubgroup.observe(this) { pairLessonsAndIsActual ->
            if (pairLessonsAndIsActual == null) {
                //something went wrong and we can't get lessons for the subgroup from the server. We start NoResponseActivity
                val intent = Intent(this, NoResponseActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                intent.putExtra(const.INTENT_KEY_NO_RESPONSE_TYPE, const.NoResponseType.GetLessons.name)
                intent.putExtra(const.INTENT_KEY_AMOUNT_ATTEMPTS_TO_CONNECT, viewModel.amountAttemptsToConnect)
                intent.putExtra(const.INTENT_KEY_SCHOOL, school)
                intent.putExtra(const.INTENT_KEY_GRADE, grade)
                intent.putExtra(const.INTENT_KEY_SUBGROUP, subgroup)
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

                val toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.nav_drawer_open, R.string.nav_drawer_close)
                toggle.syncState()

                binding.navViewMainMenu.setNavigationItemSelectedListener(this)

                viewModel.updateChosenNavViewItemId(R.id.menuItemMain)

                viewModel.liveDataChosenNavViewItemId.observe(this){ id ->
                    binding.navViewMainMenu.setCheckedItem(id)
                    val fragment = when(id) {
                        R.id.menuItemMain -> MainFragment()
                        R.id.menuItemSchedule -> ScheduleFragment()
                        R.id.menuItemTeachers -> TeachersFragment()
                        R.id.menuItemEvents -> EventsFragment()
                        else -> {
                            Log.e(const.LOG_TAG_DRAWER_INCORRECT_MENU_ITEM_ID, "The id in onNavigationItemSelected is incorrect!")
                            MainFragment()
                        }
                    }
                    supportFragmentManager.beginTransaction().replace(R.id.frameLayoutMainMenu, fragment)
                        .commit()
                }
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
                Log.e(const.LOG_TAG_DRAWER_INCORRECT_MENU_ITEM_ID, "The id in onNavigationItemSelected is incorrect!")
                MainFragment()
            }
        }

        supportFragmentManager.beginTransaction().replace(R.id.frameLayoutMainMenu, fragment)
            .commit()

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable(const.INTENT_KEY_SCHOOL, school)
        outState.putParcelable(const.INTENT_KEY_GRADE, grade)
        outState.putParcelable(const.INTENT_KEY_SUBGROUP, subgroup)
        super.onSaveInstanceState(outState)
    }

}