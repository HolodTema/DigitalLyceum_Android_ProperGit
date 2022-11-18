package com.example.lyceumapp

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
import com.example.lyceumapp.activity.ChooseGradeActivity
import com.example.lyceumapp.activity.MainMenuActivity
import com.example.lyceumapp.activity.NoResponseActivity
import com.example.lyceumapp.viewmodel.MainViewModel
import com.example.lyceumapp.databinding.ActivityChooseSchoolsOrGradesBinding
import com.example.lyceumapp.databinding.RecyclerElementSchoolsBinding
import com.example.lyceumapp.json.schools.SchoolJson
import com.example.lyceumapp.viewmodel.MainViewModelFactory


class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: SchoolJsonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_download)

        viewModel = ViewModelProvider(this, MainViewModelFactory(application, MODE_PRIVATE))[MainViewModel::class.java]

        val amountAttemptsToConnect = intent.extras?.getInt(Const.INTENT_KEY_AMOUNT_ATTEMPTS_TO_CONNECT)
        if(amountAttemptsToConnect!=null) viewModel.amountAttemptsToConnect = amountAttemptsToConnect

        viewModel.liveDataDoWeHaveDataInShPreferences.observe(this) {
            if (it) {
                startActivity(Intent(applicationContext, MainMenuActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION))
                finish()
            }
        }

        viewModel.liveDataListSchools.observe(this) { schools ->
            if (schools == null) {
                val intent = Intent(this, NoResponseActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                intent.putExtra(Const.INTENT_KEY_NO_RESPONSE_TYPE, Const.NoResponseType.GetSchools)
                intent.putExtra(Const.INTENT_KEY_AMOUNT_ATTEMPTS_TO_CONNECT, viewModel.amountAttemptsToConnect)
                startActivity(intent)
            } else {
                val binding = ActivityChooseSchoolsOrGradesBinding.inflate(layoutInflater)
                setContentView(binding.root)

                adapter = SchoolJsonAdapter(schools, layoutInflater, viewModel)
                binding.recyclerChooseSchoolOrGrade.adapter = adapter
                binding.recyclerChooseSchoolOrGrade.layoutManager = LinearLayoutManager(this)
                binding.recyclerChooseSchoolOrGrade.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

                binding.buttonNext.setOnClickListener {
                    val intent = Intent(this, ChooseGradeActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                    intent.putExtra(Const.INTENT_KEY_CHOSEN_SCHOOL, viewModel.chosenSchool)
                    startActivity(intent)
                }
            }
        }
    }

    class SchoolJsonAdapter(private val schools: List<SchoolJson>, private val inflater: LayoutInflater, private val viewModel: MainViewModel) : RecyclerView.Adapter<SchoolJsonAdapter.SchoolJsonHolder>() {
        private var checkedRadioButton: CompoundButton? = null

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            SchoolJsonHolder(RecyclerElementSchoolsBinding.inflate(inflater))

        override fun onBindViewHolder(holder: SchoolJsonHolder, position: Int) {
            holder.bindingSchoolElement.textRecyclerElementSchoolsName.text = schools[position].name

            if(schools[position].id == viewModel.chosenSchool.id) {
                holder.bindingSchoolElement.radioButtonRecyclerElementSchool.isChecked = true
                checkedRadioButton = holder.bindingSchoolElement.radioButtonRecyclerElementSchool
            }

            holder.bindingSchoolElement.radioButtonRecyclerElementSchool.setOnCheckedChangeListener { compoundButton, _ ->
                checkedRadioButton?.isChecked = false
                checkedRadioButton = compoundButton
                viewModel.chosenSchool = schools[position]
            }
        }

        override fun getItemCount() = schools.size

        class SchoolJsonHolder(val bindingSchoolElement: RecyclerElementSchoolsBinding) : RecyclerView.ViewHolder(bindingSchoolElement.root)
    }
}

