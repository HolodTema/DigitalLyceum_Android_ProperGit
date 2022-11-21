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
import com.example.lyceumapp.Const
import com.example.lyceumapp.R
import com.example.lyceumapp.databinding.ActivityChooseSchoolsOrGradesBinding
import com.example.lyceumapp.databinding.RecyclerElementGradesBinding
import com.example.lyceumapp.json.grades.GradeJson
import com.example.lyceumapp.json.schools.SchoolJson
import com.example.lyceumapp.viewmodel.ChooseGradeViewModel
import com.example.lyceumapp.viewmodel.ChooseGradeViewModelFactory

class ChooseGradeActivity : AppCompatActivity() {
    private lateinit var viewModel: ChooseGradeViewModel
    private lateinit var chosenSchool: SchoolJson
    private lateinit var adapter: ChooseGradeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_download)

        //we need to get chosenSchool in every this onCreate() method call, because our ViewModel uses it immediately
        //and that's why we need to pass chosenSchool into constructor of ViewModel. And I wish, but we need to call ViewModel's constructor every onCreate() call
        //and also that's why we need to save chosenSchool in onSaveInstanceState()
        chosenSchool = intent.extras?.getParcelable(Const.INTENT_KEY_CHOSEN_SCHOOL)!!

        viewModel = ViewModelProvider(this, ChooseGradeViewModelFactory(application, chosenSchool))[ChooseGradeViewModel::class.java]

        //we need to get amountAttemptsToConnect from intent only one time
        //if even this activity launch is the first attempt (in this case amountAttemptsToConnect = null always), we have default value of amountAttemptsToConnect in our ViewModel
        val amountAttemptsToConnect = intent.extras?.getInt(Const.INTENT_KEY_AMOUNT_ATTEMPTS_TO_CONNECT)
        //green light of variable in Android Studio is Kotlin SmartCast
        if(amountAttemptsToConnect!=null) viewModel.amountAttemptsToConnect = amountAttemptsToConnect

        //this liveData will be updated as soon as we get list of grades from server
        viewModel.liveDataListGrades.observe(this) { grades ->
            if(grades==null) {
                //here an error happened (for example no Internet, or the server is broken now)
                val intent = Intent(this, NoResponseActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                //this case we need to start NoResponseActivity with params in intent: NoResponseType, AmountAttemptsToConnect, chosenSchool
                intent.putExtra(Const.INTENT_KEY_NO_RESPONSE_TYPE, Const.NoResponseType.GetGrades)
                intent.putExtra(Const.INTENT_KEY_AMOUNT_ATTEMPTS_TO_CONNECT, viewModel.amountAttemptsToConnect)
                intent.putExtra(Const.INTENT_KEY_CHOSEN_SCHOOL, chosenSchool)
                startActivity(intent)
            }
            else if(grades.isEmpty()) {
                //this situation also can happen if Lawrence forgets to add grades for a school. And this confusing situation mustn't cause the crush of our Application
                //for this situation we have special activity NoGradeForSchoolActivity - this activity is without viewModel, because it's so simple
                val intent = Intent(this, NoGradesForSchoolActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                startActivity(intent)
            }
            else {
                //and here we got full list of grades for certain school
                //we need to update our layout, because earlier there were downloading layout
                val binding = ActivityChooseSchoolsOrGradesBinding.inflate(layoutInflater)
                setContentView(binding.root)

                //create an adapter for our RecyclerView
                adapter = ChooseGradeAdapter(grades, layoutInflater, viewModel)
                binding.recyclerChooseSchoolOrGrade.adapter = adapter
                //our recyclerView can be different: like a table, for example. But we need exactly LinearLayoutManager here
                binding.recyclerChooseSchoolOrGrade.layoutManager = LinearLayoutManager(this)
                //here we just add simple horizontal separate line between RecyclerView's elements
                binding.recyclerChooseSchoolOrGrade.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

                //and the code below works when user clicks on "Next" button
                binding.buttonNext.setOnClickListener {
                    saveEverythingInShPreferences(chosenSchool.id, viewModel.chosenGrade.id, chosenSchool.address, chosenSchool.name)
                    //finally we can start MainMenuActivity
                    startActivity(Intent(this@ChooseGradeActivity, MainMenuActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION))
                }
            }
        }
    }

    //we need to save only chosenSchool field in onSaveInstanceState()
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable(Const.INTENT_KEY_CHOSEN_SCHOOL, chosenSchool)
        super.onSaveInstanceState(outState)
    }

    //we need to save in sharedPreferences:
    //the id of chosen school
    //the id of chosen grade
    //the school address
    //the school name
    //maybe later we will need to save here school image or school mobile phone, for example...
    private fun saveEverythingInShPreferences(schoolId: Int, gradeId: Int, schoolAddress: String, schoolName: String) {
        val shPreferences = getSharedPreferences(Const.SH_PREFERENCES_NAME, MODE_PRIVATE)
        //I don't want to use .apply() instead of .commit() 'cause I'm afraid that ShPreferences can be not saved by the moment We need them in next activity - MainMenuActivity
        shPreferences.edit()
            .putInt(Const.SH_PREFERENCES_KEY_SCHOOL_ID, schoolId)
            .putInt(Const.SH_PREFERENCES_KEY_GRADE_ID, gradeId) //our architecture guarantee that gradeId will haven't be null by this code.
            .putString(Const.SH_PREFERENCES_KEY_SCHOOL_ADDRESS, schoolAddress)
            .putString(Const.SH_PREFERENCES_KEY_SCHOOL_NAME, schoolName)
            .commit()
    }

    class ChooseGradeAdapter(private val grades: List<GradeJson>, private val inflater: LayoutInflater, private val viewModel: ChooseGradeViewModel): RecyclerView.Adapter<ChooseGradeAdapter.GradeJsonHolder>() {
        private var checkedRadioButton: CompoundButton? = null

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            GradeJsonHolder(RecyclerElementGradesBinding.inflate(inflater))


        override fun onBindViewHolder(holder: GradeJsonHolder, position: Int) {
            holder.bindingGradeElement.textRecyclerElementGradesName.text = grades[position].toString()

            if(grades[position].id == viewModel.chosenGrade.id) {
                holder.bindingGradeElement.radioButtonRecyclerElementGrade.isChecked = true
                checkedRadioButton = holder.bindingGradeElement.radioButtonRecyclerElementGrade

            }

            holder.bindingGradeElement.radioButtonRecyclerElementGrade.setOnCheckedChangeListener { compoundButton, _ ->
                checkedRadioButton?.isChecked = false
                checkedRadioButton = compoundButton
                viewModel.chosenGrade = grades[position]
            }
        }

        override fun getItemCount() = grades.size

        class GradeJsonHolder(val bindingGradeElement: RecyclerElementGradesBinding) : RecyclerView.ViewHolder(bindingGradeElement.root)
    }
}