package com.example.lyceumapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.lyceumapp.AMOUNT_ATTEMPTS_TO_CONNECT_BEFORE_TIMING
import com.example.lyceumapp.DELAY_SECONDS_MANY_ATTEMPTS_TO_CONNECT
import com.example.lyceumapp.INTENT_KEY_AMOUNT_ATTEMPTS_TO_CONNECT
import com.example.lyceumapp.INTENT_KEY_AMOUNT_GRADES
import com.example.lyceumapp.INTENT_KEY_CHOSEN_GRADE
import com.example.lyceumapp.INTENT_KEY_CHOSEN_SCHOOL
import com.example.lyceumapp.INTENT_KEY_GRADE
import com.example.lyceumapp.INTENT_KEY_NO_RESPONSE_TYPE
import com.example.lyceumapp.INTENT_KEY_SCHOOL
import com.example.lyceumapp.INTENT_KEY_SUBGROUP
import com.example.lyceumapp.MainActivity
import com.example.lyceumapp.NoResponseType
import com.example.lyceumapp.R
import com.example.lyceumapp.databinding.ActivityNoResponseBinding
import com.example.lyceumapp.json.grades.GradeJson
import com.example.lyceumapp.json.schools.SchoolJson
import com.example.lyceumapp.json.subgroups.SubgroupJson
import com.example.lyceumapp.viewmodel.NoResponseViewModel
import com.example.lyceumapp.viewmodel.NoResponseViewModelFactory


//required input intent members: amountAttemptsToConnect
//can start: MainActivity, ChooseGradeActivity, ChooseSubgroupActivity
class NoResponseActivity : AppCompatActivity() {
    private lateinit var viewModel: NoResponseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityNoResponseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        //we have '!!' here, because we need to get amountAttemptsToConnect every onCreate() call
        //because we need to pass it into viewModel constructor
        //and that's why we save amountAttemptsToConnect into onSaveInstanceState()
        val amountAttemptsToConnect = intent.extras!!.getInt(INTENT_KEY_AMOUNT_ATTEMPTS_TO_CONNECT)
        viewModel = ViewModelProvider(this, NoResponseViewModelFactory(application, amountAttemptsToConnect))[NoResponseViewModel::class.java]

        //this field we need to get from intent only one time, because, unlike amountAttemptsToConnect, viewModel don't need to use it immediately
        //actually getSerializable() is deprecated now - so, we can change it to getString using enum's advantages in kotlin.
        val noResponseType = intent.extras?.getString(INTENT_KEY_NO_RESPONSE_TYPE)
        if(noResponseType!=null) viewModel.noResponseType = NoResponseType.valueOf(noResponseType)

        //and also if noResponseType's value from intent is GetGrades (it means, that we started NoResponseActivity after trying to download exactly grades)
        //this case wee need to get chosenSchool from intent. We also like in case with noResponseType need to get it from intent only one time
        //Actually, chosenSchool here is needed only for later passing into ChooseGradeActivity again...
        if(viewModel.noResponseType==NoResponseType.GetGrades) {
            val chosenSchool = intent.extras?.getParcelable<SchoolJson>(INTENT_KEY_CHOSEN_SCHOOL)
            //green color of variable in Android studio - Kotlin smartCast (usually smartCast to non-null type, like from 'Int?' to 'Int')
            if(chosenSchool!=null) viewModel.chosenSchool = chosenSchool
        }
        else if(viewModel.noResponseType==NoResponseType.GetSubgroups) {
            val chosenSchool = intent.extras?.getParcelable<SchoolJson>(INTENT_KEY_CHOSEN_SCHOOL)
            if(chosenSchool!=null) viewModel.chosenSchool = chosenSchool
            val chosenGrade = intent.extras?.getParcelable<GradeJson>(INTENT_KEY_CHOSEN_GRADE)
            if(chosenGrade!=null) viewModel.chosenGrade = chosenGrade
            val amountGrades = intent.extras?.getInt(INTENT_KEY_AMOUNT_GRADES)
            if(amountGrades!=null) viewModel.amountGrades = amountGrades
        }
        else if(viewModel.noResponseType==NoResponseType.GetLessons) {
            val school = intent.extras?.getParcelable<SchoolJson>(INTENT_KEY_SCHOOL)
            val grade = intent.extras?.getParcelable<GradeJson>(INTENT_KEY_GRADE)
            val subgroup = intent.extras?.getParcelable<SubgroupJson>(INTENT_KEY_SUBGROUP)
            if(school!=null && grade!=null && subgroup!=null) {
                viewModel.school = school
                viewModel.grade = grade
                viewModel.subgroup = subgroup
            }
        }

        //not only timer in our ViewModel starts in case of many requests to server from user. We also need to hide some xml-elements, for example button 'Try again'
        if(viewModel.amountAttemptsToConnect>=AMOUNT_ATTEMPTS_TO_CONNECT_BEFORE_TIMING) {
            binding.buttonTryAgain.visibility = View.GONE
            binding.textYouSendSoManyRequests.text = String.format(resources.getString(R.string.amount_attempts_to_server_limit), DELAY_SECONDS_MANY_ATTEMPTS_TO_CONNECT)
        }
        else {
            binding.textYouSendSoManyRequests.visibility = View.GONE
        }


        binding.buttonTryAgain.setOnClickListener{
            val intent = when(viewModel.noResponseType) {
                NoResponseType.GetSchools -> {
                    Intent(this, MainActivity::class.java)
                }
                NoResponseType.GetGrades -> {
                    Intent(this, ChooseGradeActivity::class.java)
                        .putExtra(INTENT_KEY_CHOSEN_SCHOOL, viewModel.chosenSchool)
                }
                NoResponseType.GetSubgroups -> {
                    Intent(this, ChooseSubgroupActivity::class.java)
                        .putExtra(INTENT_KEY_CHOSEN_SCHOOL, viewModel.chosenSchool)
                        .putExtra(INTENT_KEY_CHOSEN_GRADE, viewModel.chosenGrade)
                        .putExtra(INTENT_KEY_AMOUNT_GRADES, viewModel.amountGrades)
                }
                NoResponseType.GetLessons -> {
                    Intent(this, MainMenuActivity::class.java)
                        .putExtra(INTENT_KEY_SCHOOL, viewModel.school)
                        .putExtra(INTENT_KEY_GRADE, viewModel.grade)
                        .putExtra(INTENT_KEY_SUBGROUP, viewModel.subgroup)
                }
            }
            viewModel.amountAttemptsToConnect++;
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            intent.putExtra(INTENT_KEY_AMOUNT_ATTEMPTS_TO_CONNECT, viewModel.amountAttemptsToConnect)
            startActivity(intent)
        }

        //this liveData contains amount of seconds in timer from ViewModel
        viewModel.liveDataCountDownTimerSeconds.observe(this) {
            if(it==null) { //here timer has finished
                binding.textYouSendSoManyRequests.visibility = View.GONE
                binding.buttonTryAgain.visibility = View.VISIBLE
            }
            else { //here timer is still running. Here we just need to update text with new seconds value
                binding.textYouSendSoManyRequests.text = String.format(resources.getString(R.string.amount_attempts_to_server_limit), it)
            }
        }
    }

    //and in onSaveInstanceState we need to save only one value - amountAttemptsToConnect, because this field is only one, that our ViewModel uses immediately
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(INTENT_KEY_AMOUNT_ATTEMPTS_TO_CONNECT, viewModel.amountAttemptsToConnect)
        super.onSaveInstanceState(outState)
    }
}