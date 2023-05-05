package com.example.lyceumapp.activity

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lyceumapp.INTENT_KEY_AMOUNT_ATTEMPTS_TO_CONNECT
import com.example.lyceumapp.INTENT_KEY_AMOUNT_GRADES
import com.example.lyceumapp.INTENT_KEY_CHOSEN_GRADE
import com.example.lyceumapp.INTENT_KEY_CHOSEN_SCHOOL
import com.example.lyceumapp.INTENT_KEY_NO_RESPONSE_TYPE
import com.example.lyceumapp.MainActivity
import com.example.lyceumapp.NoResponseType
import com.example.lyceumapp.R
import com.example.lyceumapp.databinding.ActivityChooseGradesOrSubgroupsBinding
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
        supportActionBar?.hide()

        //we need to get chosenSchool in every this onCreate() method call, because our ViewModel uses it immediately
        //and that's why we need to pass chosenSchool into constructor of ViewModel. And I wish, but we need to call ViewModel's constructor every onCreate() call
        //and also that's why we need to save chosenSchool in onSaveInstanceState()
        chosenSchool = intent.extras?.getParcelable(INTENT_KEY_CHOSEN_SCHOOL)!!

        viewModel = ViewModelProvider(
            this,
            ChooseGradeViewModelFactory(application, chosenSchool)
        )[ChooseGradeViewModel::class.java]

        //this liveData will be updated as soon as we get list of grades from server
        viewModel.liveDataListGrades.observe(this) { grades ->
            if (grades == null) {
                //here an error happened (for example no Internet, or the server is broken now)
                val intent = Intent(this, NoResponseActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                //this case we need to start NoResponseActivity with params in intent: NoResponseType, AmountAttemptsToConnect, chosenSchool
                intent.putExtra(
                    INTENT_KEY_NO_RESPONSE_TYPE,
                    NoResponseType.GetGrades.name
                )
                intent.putExtra(
                    INTENT_KEY_AMOUNT_ATTEMPTS_TO_CONNECT,
                    viewModel.amountAttemptsToConnect
                )
                intent.putExtra(INTENT_KEY_CHOSEN_SCHOOL, chosenSchool)
                startActivity(intent)
            } else if (grades.isEmpty()) {
                //this situation also can happen if Lawrence forgets to add grades for a school. And this confusing situation mustn't cause the crush of our Application
                //for this situation we have special activity NoGradeForSchoolActivity - this activity is without viewModel, because it's so simple
                val intent = Intent(this, NoGradesForSchoolActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                startActivity(intent)
            } else {
                //and here we got full list of grades for certain school
                //we need to update our layout, because earlier there were downloading layout
                val binding = ActivityChooseGradesOrSubgroupsBinding.inflate(layoutInflater)
                setContentView(binding.root)

                //create an adapter for our RecyclerView
                adapter = ChooseGradeAdapter(grades, viewModel)
                binding.recyclerChooseGradeOrSubgroup.adapter = adapter
                //our recyclerView can be different: like a table, for example. But we need exactly LinearLayoutManager here
                binding.recyclerChooseGradeOrSubgroup.layoutManager = LinearLayoutManager(this)
                //here we just add simple horizontal separate line between RecyclerView's elements
                binding.recyclerChooseGradeOrSubgroup.addItemDecoration(
                    DividerItemDecoration(
                        this,
                        DividerItemDecoration.VERTICAL
                    )
                )

                //and the code below works when user clicks on "Next" button
                binding.buttonNext.setOnClickListener {
                    //here we need to download subgroups for the chosenGrade
                    val intent = Intent(this, ChooseSubgroupActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                    //we need to pass chosenGrade, chosenSchool and amountGrades into the ChooseSubgroupActivity
                    intent.putExtra(INTENT_KEY_CHOSEN_SCHOOL, chosenSchool)
                    intent.putExtra(INTENT_KEY_CHOSEN_GRADE, viewModel.chosenGrade)
                    intent.putExtra(
                        INTENT_KEY_AMOUNT_GRADES,
                        viewModel.liveDataListGrades.value?.size
                    )
                    startActivity(intent)
                }

                //the code below works when user clicks on "cancel" button
                //this case we need to start MainActivity (there will be schools list in MainActivity)
                binding.buttonCancel.setOnClickListener {
                    val intent = Intent(this, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                    startActivity(intent)
                }
            }
        }
    }

    //we need to save only chosenSchool field in onSaveInstanceState()
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable(INTENT_KEY_CHOSEN_SCHOOL, chosenSchool)
        super.onSaveInstanceState(outState)
    }


    class ChooseGradeAdapter(
        private val grades: List<GradeJson>,
        private val viewModel: ChooseGradeViewModel
    ) : RecyclerView.Adapter<ChooseGradeAdapter.GradeJsonHolder>() {
        private var checkedRadioButton: CompoundButton? = null

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            GradeJsonHolder(
                RecyclerElementGradesBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )


        override fun onBindViewHolder(holder: GradeJsonHolder, position: Int) {
            holder.bindingGradeElement.textRecyclerElementGradesName.text =
                grades[position].toString()

            if (grades[position].id == viewModel.chosenGrade.id) {
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

        class GradeJsonHolder(val bindingGradeElement: RecyclerElementGradesBinding) :
            RecyclerView.ViewHolder(bindingGradeElement.root)
    }
}