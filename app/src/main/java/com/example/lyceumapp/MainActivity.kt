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
import com.example.lyceumapp.activity.NoSchoolsActivity
import com.example.lyceumapp.databinding.ActivityChooseSchoolsBinding
import com.example.lyceumapp.viewmodel.MainViewModel
import com.example.lyceumapp.databinding.RecyclerElementSchoolsBinding
import com.example.lyceumapp.json.schools.SchoolJson
import com.example.lyceumapp.viewmodel.MainViewModelFactory


class MainActivity : AppCompatActivity() {
    //viewModel for MainActivity
    private lateinit var viewModel: MainViewModel
    //adapter for recyclerView for list of schools
    private lateinit var adapter: SchoolJsonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //download layout is the first, because user must see that his request is downloading now
        setContentView(R.layout.activity_download)
        supportActionBar?.hide()

        //we create viewModel using ViewModelProvider. Actually, MainViewModel creates only one time when activity creates the first time.
        //but we still need delegate some parameters into MainViewModelConstructor every onCreate() call
        //the reason above - that's why we need to save some data to intent
        viewModel = ViewModelProvider(this, MainViewModelFactory(application, MODE_PRIVATE))[MainViewModel::class.java]

        //we need this field 'amountAttemptsToConnect', because we need to show delay timer (like against ddos-attacks) if amountAttemptsToConnect > some value in Const.kt
        //if even this activity launch is the first attempt (in this case amountAttemptsToConnect = null always), we have default value of amountAttemptsToConnect in our ViewModel
        val amountAttemptsToConnect = intent.extras?.getInt(Const.INTENT_KEY_AMOUNT_ATTEMPTS_TO_CONNECT)
        //we need to get amountAttemptsToConnect from intent only the first time activity creates
        //and we set this value from intent into ourViewModel instance
        if(amountAttemptsToConnect!=null) viewModel.amountAttemptsToConnect = amountAttemptsToConnect

        //view (here view is MainActivity) can subscribe on all events that can happen with certain LiveData field in ViewModel
        //when something with liveData happens, the code below will run
        //this example: we have liveDataDoWeHaveDataInShPreferences that contains boolean value
        //if the boolean value == true, user is already chosen school and grade before and we need to go to MainMenuActivity
        //if the boolean value == false, user hasn't chosen school and grade yet, so we need to continue work in MainActivity and we need to download schools for user
        viewModel.liveDataDoWeHaveDataInShPreferences.observe(this) {
            if (it) {
                //Intent.FLAG_ACTIVITY_N0_ANIMATION - start another activity without animation
                startActivity(Intent(applicationContext, MainMenuActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION))
                //when we finish our activity, MainViewModel will be destroyed
                finish()
            }
        }

        //the code below will run when we get some value from RequestManager (and from RetrofitManager and from Lawrence API)
        //this value is our List<SchoolJson>?
        //it can be null if some error happen (for example, no Internet, or Lawrence API isn't working now)
        //and also wee need to check the size of List<SchoolJson>. if size==0, it means that API's working now, but there's no schools in API
        //empty schools list in API is so strange, but it can happen. So, we need to predict this situation in our application
        viewModel.liveDataListSchools.observe(this) { schools ->
            if (schools == null) {
                //here some error happen and we haven't got schools. We need to start NoResponseActivity
                //we give some values through Intent into NoResponseActivity: Const.NoResponseType and amountAttemptsToConnect
                //NoResponseType - NoResponseActivity must know if error happens during downloading schools or during downloading grades.
                //AmountAttemptToConnect - if this value > than some value from Const, we need to show delay timing (ddos attack blocking)
                val intent = Intent(this, NoResponseActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                intent.putExtra(Const.INTENT_KEY_NO_RESPONSE_TYPE, Const.NoResponseType.GetSchools)
                intent.putExtra(Const.INTENT_KEY_AMOUNT_ATTEMPTS_TO_CONNECT, viewModel.amountAttemptsToConnect)
                startActivity(intent)
            } else if(schools.isEmpty()) {
                //here the unlikely situation, where the server works, but returns an empty school list
                //this case we need to launch very simple activity - NoSchoolsActivity
                val intent = Intent(this, NoSchoolsActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                startActivity(intent)
            }
            else {
                //we got schools successfully!
                //ViewBinding is very cool technique from Google. We can use it instead of findViewById() calls
                val binding = ActivityChooseSchoolsBinding.inflate(layoutInflater)
                setContentView(binding.root)

                //create an adapter for RecyclerView for schools list visualisation
                adapter = SchoolJsonAdapter(schools, viewModel)
                binding.recyclerChooseSchool.adapter = adapter
                //we need to set layoutManager to recyclerView (a recycler view can be like table for example, but we need LinearLayoutManager)
                binding.recyclerChooseSchool.layoutManager = (LinearLayoutManager(this))
                //here we add separating line between elements in recyclerView
                binding.recyclerChooseSchool.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

                //the code below works if we click button 'next' under RecyclerView. When the button clicked, we need to get chosenSchool object from adapter and start ChooseGradeActivity
                binding.buttonNext.setOnClickListener {
                    val intent = Intent(this, ChooseGradeActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                    //chosenSchool - the school object that was chosen by user in RecyclerView. This object saved in our viewModel
                    //and we need to pass this chosenSchool into ChooseGradeActivity
                    intent.putExtra(Const.INTENT_KEY_CHOSEN_SCHOOL, viewModel.chosenSchool)
                    startActivity(intent)
                }
            }
        }
    }

    //our adapter for RecyclerView
    //we pass viewModel into adapter object, because chosenSchool object must be saved exactly in ViewModel - because in case of activity configuration changes it'll be saved
    class SchoolJsonAdapter(private val schools: List<SchoolJson>, private val viewModel: MainViewModel) : RecyclerView.Adapter<SchoolJsonAdapter.SchoolJsonHolder>() {
        //the object of checked by user RadioButton
        private var checkedRadioButton: CompoundButton? = null

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            SchoolJsonHolder(RecyclerElementSchoolsBinding.inflate(LayoutInflater.from(parent.context), parent, false))

        override fun onBindViewHolder(holder: SchoolJsonHolder, position: Int) {
            //we set school name textView in every element of our RecyclerView
            holder.bindingSchoolElement.textSchoolName.text = schools[position].name
            holder.bindingSchoolElement.textSchoolAddress.text = schools[position].address

            //if school was chosen by user earlier we show it to user in our RadioButton in element of RecyclerView
            if(schools[position].id == viewModel.chosenSchool.id) {
                holder.bindingSchoolElement.radioChooseSchool.isChecked = true
                checkedRadioButton = holder.bindingSchoolElement.radioChooseSchool
            }

            //and there's click listener for our RadioButton
            //we need to set viewModel's field 'chosenSchool' evevalry time some radioButton is clicked
            //and in adapter we always need to save the object of checked RadioButton - it saves in 'checkedRadioButton'
            holder.bindingSchoolElement.radioChooseSchool.setOnCheckedChangeListener { compoundButton, _ ->
                checkedRadioButton?.isChecked = false
                checkedRadioButton = compoundButton
                viewModel.chosenSchool = schools[position]
            }
        }

        override fun getItemCount() = schools.size

        //we also can use ViewBinding in viewHolder
        class SchoolJsonHolder(val bindingSchoolElement: RecyclerElementSchoolsBinding) : RecyclerView.ViewHolder(bindingSchoolElement.root)
    }
}

