package com.example.lyceumapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.lyceumapp.Const
import com.example.lyceumapp.MainActivity
import com.example.lyceumapp.R
import com.example.lyceumapp.databinding.ActivityNoResponseBinding
import com.example.lyceumapp.json.schools.SchoolJson
import com.example.lyceumapp.viewmodel.NoResponseViewModel
import com.example.lyceumapp.viewmodel.NoResponseViewModelFactory

class NoResponseActivity : AppCompatActivity() {
    private lateinit var viewModel: NoResponseViewModel
    private lateinit var chosenSchool: SchoolJson

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityNoResponseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, NoResponseViewModelFactory(application))[NoResponseViewModel::class.java]

        // TODO: in NoResponseViewModel init{} we check amountAttemptsToConnect and it's always = 1 there!. We need to pass amountAttemptsToConnect to viewModel's constructor
        val amountAttemptsToConnect = intent.extras?.getInt(Const.INTENT_KEY_AMOUNT_ATTEMPTS_TO_CONNECT)
        if(amountAttemptsToConnect!=null) {
            viewModel.amountAttemptsToConnect = amountAttemptsToConnect
        }

        val noResponseType = intent.extras?.getSerializable(Const.INTENT_KEY_NO_RESPONSE_TYPE) as Const.NoResponseType
        viewModel.noResponseType = noResponseType

        if(viewModel.noResponseType==Const.NoResponseType.GetGrades) {
            chosenSchool = intent.extras?.getParcelable(Const.INTENT_KEY_CHOSEN_SCHOOL)!!
        }

        if(viewModel.amountAttemptsToConnect>=Const.AMOUNT_ATTEMPTS_TO_CONNECT_BEFORE_TIMING) {
            binding.textLikeButtonNoResponseTryAgain.visibility = View.GONE
            binding.textYouSendSoManyRequests.text = String.format(resources.getString(R.string.amount_attempts_to_server_limit), Const.DELAY_SECONDS_MANY_ATTEMPTS_TO_CONNECT)
        }
        else {
            binding.textYouSendSoManyRequests.visibility = View.GONE
        }

        binding.textLikeButtonNoResponseTryAgain.setOnClickListener{
            val intent = when(viewModel.noResponseType) {
                Const.NoResponseType.GetSchools -> {
                    Intent(this, MainActivity::class.java)
                }
                Const.NoResponseType.GetGrades -> {
                    Intent(this, ChooseGradeActivity::class.java).putExtra(Const.INTENT_KEY_CHOSEN_SCHOOL, chosenSchool)
                }
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            intent.putExtra(Const.INTENT_KEY_AMOUNT_ATTEMPTS_TO_CONNECT, viewModel.amountAttemptsToConnect)
            startActivity(intent)
        }

        viewModel.liveDataCountDownTimerSeconds.observe(this) {
            if(it==null) { //here timer has finished
                binding.textYouSendSoManyRequests.visibility = View.GONE
                binding.textLikeButtonNoResponseTryAgain.visibility = View.VISIBLE
            }
            else {
                binding.textYouSendSoManyRequests.text = String.format(resources.getString(R.string.amount_attempts_to_server_limit), it)
            }
        }
    }
}