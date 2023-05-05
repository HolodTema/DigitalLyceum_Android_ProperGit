package com.example.lyceumapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lyceumapp.INTENT_KEY_AMOUNT_GRADES
import com.example.lyceumapp.INTENT_KEY_CHOSEN_SCHOOL
import com.example.lyceumapp.MainActivity
import com.example.lyceumapp.R
import com.example.lyceumapp.databinding.ActivityNoSubgroupsForGradeBinding
import com.example.lyceumapp.json.schools.SchoolJson

class NoSubgroupsForGradeActivity : AppCompatActivity() {
    private var amountGrades = -1
    private lateinit var chosenSchool: SchoolJson

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityNoSubgroupsForGradeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        amountGrades = intent.extras!!.getInt(INTENT_KEY_AMOUNT_GRADES)
        chosenSchool = intent.extras!!.getParcelable(INTENT_KEY_CHOSEN_SCHOOL)!!

        if(amountGrades>1) {
            //here we need to show text exactly about grade, because we have more than one grade in chosenSchool
            binding.textNoSubgroupsForGradeDescription.text = resources.getString(R.string.no_subgroups_for_grade_description)
            binding.buttonPickGradeOrSchoolAgain.text = resources.getString(R.string.pick_grade_again)
            binding.buttonPickGradeOrSchoolAgain.setOnClickListener{
                val intent = Intent(this, ChooseGradeActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                intent.putExtra(INTENT_KEY_CHOSEN_SCHOOL, chosenSchool)
                startActivity(intent)
            }
        }
        else {
            //here we have only one grade with empty subgroups list. We need to show a text exactly about school
            binding.textNoSubgroupsForGradeDescription.text = resources.getString(R.string.no_subgroups_for_school_description)
            binding.buttonPickGradeOrSchoolAgain.text = resources.getString(R.string.pick_school_again)
            binding.buttonPickGradeOrSchoolAgain.setOnClickListener{
                val intent = Intent(this, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                startActivity(intent)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(INTENT_KEY_AMOUNT_GRADES, amountGrades)
        outState.putParcelable(INTENT_KEY_CHOSEN_SCHOOL, chosenSchool)
        super.onSaveInstanceState(outState)
    }
}