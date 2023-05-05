package com.example.lyceumapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lyceumapp.INTENT_KEY_AMOUNT_ATTEMPTS_TO_CONNECT
import com.example.lyceumapp.INTENT_KEY_AMOUNT_GRADES
import com.example.lyceumapp.INTENT_KEY_CHOSEN_GRADE
import com.example.lyceumapp.INTENT_KEY_CHOSEN_SCHOOL
import com.example.lyceumapp.INTENT_KEY_GRADE
import com.example.lyceumapp.INTENT_KEY_NO_RESPONSE_TYPE
import com.example.lyceumapp.INTENT_KEY_SCHOOL
import com.example.lyceumapp.INTENT_KEY_SUBGROUP
import com.example.lyceumapp.NoResponseType
import com.example.lyceumapp.R
import com.example.lyceumapp.SH_PREFERENCES_KEY_SUBGROUP_ID
import com.example.lyceumapp.SH_PREFERENCES_NAME
import com.example.lyceumapp.databinding.ActivityChooseGradesOrSubgroupsBinding
import com.example.lyceumapp.databinding.RecyclerElementSubgroupsBinding
import com.example.lyceumapp.json.grades.GradeJson
import com.example.lyceumapp.json.schools.SchoolJson
import com.example.lyceumapp.json.subgroups.SubgroupJson
import com.example.lyceumapp.viewmodel.ChooseSubgroupViewModel
import com.example.lyceumapp.viewmodel.ChooseSubgroupViewModelFactory

class ChooseSubgroupActivity : AppCompatActivity() {
    private lateinit var viewModel: ChooseSubgroupViewModel
    private lateinit var chosenGrade: GradeJson
    private lateinit var adapter: ChooseSubgroupAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_download)
        supportActionBar?.hide()

        //we need to get chosenGradeFromIntent in every this onCreate() method call, because
        //our ViewModel use it in it's constructor every time we have onCreate() call
        chosenGrade = intent.extras?.getParcelable(INTENT_KEY_CHOSEN_GRADE)!!

        viewModel = ViewModelProvider(this,
                ChooseSubgroupViewModelFactory(application, chosenGrade))[ChooseSubgroupViewModel::class.java]

        //we need to get chosenSchool only one time, because chosenSchool is needed to our viewModel only when button "next" was clicked.
        //and this case we need to save chosenSchool.id into shPreferences
        val chosenSchool = intent.extras?.getParcelable<SchoolJson>(INTENT_KEY_CHOSEN_SCHOOL)
        if(chosenSchool!=null) viewModel.chosenSchool = chosenSchool

        //we need to get amountAttemptsToConnect from intent only one time
        //if even this activity's launch is the first attempt to connect (in this case the code row below will be
        //null), we anyway set amountAttemptsToConnect = 1 in ViewModel
        val amountAttemptsToConnect = intent.extras?.getInt(INTENT_KEY_AMOUNT_ATTEMPTS_TO_CONNECT)
        if(amountAttemptsToConnect!=null) viewModel.amountAttemptsToConnect = amountAttemptsToConnect

        // TODO: we haven't made amountGrades passing through intent in ChooseGradeActivity
        //we may have situation when there are no subgroups for a grade and a school, that have been chosen earlier, contains only one grade. This case it's no good showing
        //a list of the grades to user, we need to show list of schools to user. And that's why we need to get amountOfGrades to check this condition and do some actions if necessary
        val amountGrades = intent.extras?.getInt(INTENT_KEY_AMOUNT_GRADES)
        if(amountGrades!=null) viewModel.amountGrades = amountGrades

        viewModel.liveDataListSubgroups.observe(this) { subgroups ->
            if(subgroups==null) {
                //here we need to start NoResponseActivity
                val intent = Intent(this, NoResponseActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                //we need to pass noResponseType, amountAttemptsToConnect, chosenSchool, chosenGrade, amountGrades into NoResponseActivity
                intent.putExtra(INTENT_KEY_NO_RESPONSE_TYPE, NoResponseType.GetSubgroups.name)
                intent.putExtra(INTENT_KEY_AMOUNT_ATTEMPTS_TO_CONNECT, viewModel.amountAttemptsToConnect)
                intent.putExtra(INTENT_KEY_CHOSEN_SCHOOL, viewModel.chosenSchool)
                intent.putExtra(INTENT_KEY_CHOSEN_GRADE, chosenGrade)
                intent.putExtra(INTENT_KEY_AMOUNT_GRADES, viewModel.amountGrades)
                startActivity(intent)
            }
            else if(subgroups.isEmpty()) {
                //this case we need to start NoSubgroupsForGradeActivity and we need to pass there amountGrades for property working
                val intent = Intent(this, NoSubgroupsForGradeActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                intent.putExtra(INTENT_KEY_AMOUNT_GRADES, viewModel.amountGrades)
                intent.putExtra(INTENT_KEY_CHOSEN_SCHOOL, viewModel.chosenSchool)
                startActivity(intent)
            }
            else if(subgroups.size==1) {
                //here it's no good showing the list of subgroups, because there is only one subgroup. We need to save everything in shPreferences and start MainMenuActivity
                saveEverythingInShPreferences(subgroups[0].id)
                val intent = Intent(this, MainMenuActivity::class.java)
                intent.putExtra(INTENT_KEY_SCHOOL, viewModel.chosenSchool)
                    .putExtra(INTENT_KEY_GRADE, chosenGrade)
                    .putExtra(INTENT_KEY_SUBGROUP, viewModel.chosenSubgroup)
                startActivity(intent)
            }
            else {
                //and here we have enough size list of subgroups (size>=2) and we need to show layout with recyclerView and adapter for this recycler view
                val binding = ActivityChooseGradesOrSubgroupsBinding.inflate(layoutInflater)
                setContentView(binding.root)

                //create an adapter for our RecyclerView
                adapter = ChooseSubgroupAdapter(subgroups, layoutInflater, viewModel)
                binding.recyclerChooseGradeOrSubgroup.adapter = adapter
                //configure our recyclerView visualisation
                binding.recyclerChooseGradeOrSubgroup.layoutManager = LinearLayoutManager(this)
                binding.recyclerChooseGradeOrSubgroup.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

                //listener for "next" button
                binding.buttonNext.setOnClickListener {
                    //here we need save everything in sharedPreferences and finally start MainMenuActivity
                    saveEverythingInShPreferences(viewModel.chosenSubgroup.id)
                    val intent = Intent(this, MainMenuActivity::class.java)
                    intent.putExtra(INTENT_KEY_SCHOOL, viewModel.chosenSchool)
                        .putExtra(INTENT_KEY_GRADE, chosenGrade)
                        .putExtra(INTENT_KEY_SUBGROUP, viewModel.chosenSubgroup)
                    startActivity(intent)
                }

                //listener for "cancel" button.
                binding.buttonCancel.setOnClickListener{
                    //we need to start chooseGradeActivity with chosenSchoolId
                    val intent = Intent(this, ChooseGradeActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                    intent.putExtra(INTENT_KEY_CHOSEN_SCHOOL, viewModel.chosenSchool)
                    startActivity(intent)
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable(INTENT_KEY_CHOSEN_GRADE, chosenGrade)
        super.onSaveInstanceState(outState)
    }

    //we need to save in sharedPreferences:
    //the id of chosen subgroup
    private fun saveEverythingInShPreferences(subgroupId: Int) {
        val shPreferences = getSharedPreferences(SH_PREFERENCES_NAME, MODE_PRIVATE)
        //I don't want to use .apply() instead of .commit() 'cause I'm afraid that ShPreferences can be not saved by the moment We need them in next activity - MainMenuActivity
        shPreferences.edit().putInt(SH_PREFERENCES_KEY_SUBGROUP_ID, subgroupId).commit()
    }


    class ChooseSubgroupAdapter(
        private val subgroups: List<SubgroupJson>,
        private val inflater: LayoutInflater,
        private val viewModel: ChooseSubgroupViewModel):
        RecyclerView.Adapter<ChooseSubgroupAdapter.SubgroupJsonHolder>() {
        private var checkedRadioButton: CompoundButton? = null

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            SubgroupJsonHolder(RecyclerElementSubgroupsBinding.inflate(inflater))

        override fun onBindViewHolder(holder: SubgroupJsonHolder, position: Int) {
            holder.bindingSubgroupElement.textRecyclerElementSubgroupsName.text = subgroups[position].name

            if(subgroups[position].id == viewModel.chosenSubgroup.id) {
                holder.bindingSubgroupElement.radioButtonRecyclerElementSubgroup.isChecked = true
                checkedRadioButton = holder.bindingSubgroupElement.radioButtonRecyclerElementSubgroup
            }

            holder.bindingSubgroupElement.radioButtonRecyclerElementSubgroup.setOnCheckedChangeListener{ compoundButton, _ ->
                checkedRadioButton?.isChecked = false
                checkedRadioButton = compoundButton
                viewModel.chosenSubgroup = subgroups[position]
            }
        }

        override fun getItemCount() = subgroups.size

        class SubgroupJsonHolder(val bindingSubgroupElement: RecyclerElementSubgroupsBinding): RecyclerView.ViewHolder(bindingSubgroupElement.root)
    }
}