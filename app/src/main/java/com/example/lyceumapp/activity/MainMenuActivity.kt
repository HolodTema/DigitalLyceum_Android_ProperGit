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
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import com.example.lyceumapp.INTENT_KEY_AMOUNT_ATTEMPTS_TO_CONNECT
import com.example.lyceumapp.INTENT_KEY_GRADE
import com.example.lyceumapp.INTENT_KEY_NO_RESPONSE_TYPE
import com.example.lyceumapp.INTENT_KEY_SCHOOL
import com.example.lyceumapp.INTENT_KEY_SUBGROUP
import com.example.lyceumapp.LOG_TAG_DRAWER_INCORRECT_MENU_ITEM_ID
import com.example.lyceumapp.NoResponseType
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

        initLateinitFieldsFromIntent()

        viewModel = ViewModelProvider(this, MainMenuViewModelFactory(application, school, grade, subgroup))[MainMenuViewModel::class.java]

        val amountAttemptsToConnect = intent.extras?.getInt(INTENT_KEY_AMOUNT_ATTEMPTS_TO_CONNECT)
        if(amountAttemptsToConnect!=null) viewModel.amountAttemptsToConnect = amountAttemptsToConnect

        viewModel.liveDataNearestDayLessons.observe(this){
            if(it==null) {
                //something went wrong and we can't get lessons for the subgroup from the server. We start NoResponseActivity
                startNoResponseActivity(school, grade, subgroup, viewModel.amountAttemptsToConnect)
            }
            else {
                //there is we've finally got lessons. We can show main_menu layout!
                val binding = ActivityMainMenuBinding.inflate(layoutInflater)
                setContentView(binding.root)

                drawerLayout = binding.drawerLayoutMainMenu
                val toolbar = findViewById<Toolbar>(R.id.toolbarMainMenu)
                val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_drawer_open, R.string.nav_drawer_close)
                drawerLayout.addDrawerListener(toggle)
                toggle.syncState()

                binding.navViewMainMenu.setNavigationItemSelectedListener(this)

                viewModel.updateChosenNavViewItemId(viewModel.liveDataChosenNavViewItemId.value ?: R.id.menuItemMain)

                viewModel.liveDataChosenNavViewItemId.observe(this){ id ->
                    binding.navViewMainMenu.setCheckedItem(id)
                    val fragment = when(id) {
                        R.id.menuItemMain -> MainFragment()
                        R.id.menuItemSchedule -> ScheduleFragment()
                        R.id.menuItemTeachers -> TeachersFragment()
                        R.id.menuItemEvents -> EventsFragment()
                        else -> {
                            Log.e(LOG_TAG_DRAWER_INCORRECT_MENU_ITEM_ID, "The id in onNavigationItemSelected is incorrect!")
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
        viewModel.updateChosenNavViewItemId(item.itemId)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable(INTENT_KEY_SCHOOL, school)
        outState.putParcelable(INTENT_KEY_GRADE, grade)
        outState.putParcelable(INTENT_KEY_SUBGROUP, subgroup)
        super.onSaveInstanceState(outState)
    }

    private fun initLateinitFieldsFromIntent() {
        school = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) intent.extras!!.getParcelable(INTENT_KEY_SCHOOL, SchoolJson::class.java)!!
        else intent.extras!!.getParcelable(INTENT_KEY_SCHOOL)!!

        grade = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) intent.extras!!.getParcelable(INTENT_KEY_GRADE, GradeJson::class.java)!!
        else intent.extras!!.getParcelable(INTENT_KEY_GRADE)!!

        subgroup = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) intent.extras!!.getParcelable(INTENT_KEY_SUBGROUP, SubgroupJson::class.java)!!
        else intent.extras!!.getParcelable(INTENT_KEY_SUBGROUP)!!
    }

    private fun startNoResponseActivity(school: SchoolJson, grade: GradeJson, subgroup: SubgroupJson, amountAttemptsToConnect: Int) {
        val intent = Intent(this, NoResponseActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        intent.putExtra(INTENT_KEY_NO_RESPONSE_TYPE, NoResponseType.GetLessons.name)
        intent.putExtra(INTENT_KEY_AMOUNT_ATTEMPTS_TO_CONNECT, amountAttemptsToConnect)
        intent.putExtra(INTENT_KEY_SCHOOL, school)
        intent.putExtra(INTENT_KEY_GRADE, grade)
        intent.putExtra(INTENT_KEY_SUBGROUP, subgroup)
        startActivity(intent)
    }
}