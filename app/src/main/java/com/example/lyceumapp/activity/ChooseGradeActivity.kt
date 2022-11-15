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

        chosenSchool = intent.extras?.getParcelable(Const.INTENT_KEY_CHOSEN_SCHOOL)!!

        viewModel = ViewModelProvider(this, ChooseGradeViewModelFactory(application, chosenSchool))[ChooseGradeViewModel::class.java]

        val amountAttemptsToConnect = intent.extras?.getInt(Const.INTENT_KEY_AMOUNT_ATTEMPTS_TO_CONNECT)
        if(amountAttemptsToConnect!=null) viewModel.amountAttemptsToConnect = amountAttemptsToConnect

        viewModel.liveDataListGrades.observe(this) { grades ->
            if(grades==null) {
                val intent = Intent(this, NoResponseActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                intent.putExtra(Const.INTENT_KEY_NO_RESPONSE_TYPE, Const.NoResponseType.GetGrades)
                intent.putExtra(Const.INTENT_KEY_AMOUNT_ATTEMPTS_TO_CONNECT, viewModel.amountAttemptsToConnect)
                intent.putExtra(Const.INTENT_KEY_CHOSEN_SCHOOL, chosenSchool)
                startActivity(intent)
            }
            else {
                val binding = ActivityChooseSchoolsOrGradesBinding.inflate(layoutInflater)
                setContentView(binding.root)

                adapter = ChooseGradeAdapter(grades, layoutInflater, viewModel)
                binding.recyclerChooseSchoolOrGrade.adapter = adapter
                binding.recyclerChooseSchoolOrGrade.layoutManager = LinearLayoutManager(this)
                binding.recyclerChooseSchoolOrGrade.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

                binding.buttonNext.setOnClickListener {
                    saveEverythingInShPreferences(chosenSchool.id, viewModel.chosenGrade.id, chosenSchool.address, chosenSchool.name)
                    startActivity(Intent(this@ChooseGradeActivity, MainMenuActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION))
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable(Const.INTENT_KEY_CHOSEN_SCHOOL, chosenSchool)
        super.onSaveInstanceState(outState)
    }

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