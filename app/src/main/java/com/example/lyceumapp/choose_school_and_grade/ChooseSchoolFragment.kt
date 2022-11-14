package com.example.lyceumapp.choose_school_and_grade

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lyceumapp.Const
import com.example.lyceumapp.databinding.FragmentChooseSchoolBinding
import com.example.lyceumapp.databinding.RecyclerElementSchoolsBinding
import com.example.lyceumapp.json.schools.SchoolJson


class ChooseSchoolFragment : Fragment() {
    lateinit var adapter: SchoolJsonAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentChooseSchoolBinding.inflate(inflater, container, false)

        adapter = SchoolJsonAdapter(
            requireArguments().getParcelableArrayList(Const.BUNDLE_KEY_SCHOOLS_LIST)!!,
            inflater
        )
        binding.recyclerChooseSchool.adapter = adapter
        binding.recyclerChooseSchool.layoutManager = LinearLayoutManager(context)
        binding.recyclerChooseSchool.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )

        return binding.root
    }


    class SchoolJsonAdapter(
        private val schools: ArrayList<SchoolJson>,
        private val inflater: LayoutInflater
    ) : RecyclerView.Adapter<SchoolJsonAdapter.SchoolJsonHolder>() {
        var chosenSchool = schools[0]
        private var checkedRadioButton: CompoundButton? = null

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            SchoolJsonHolder(RecyclerElementSchoolsBinding.inflate(inflater))

        override fun onBindViewHolder(holder: SchoolJsonHolder, position: Int) {
            holder.bindingSchoolElement.textRecyclerElementSchoolsName.text = schools[position].name

            holder.bindingSchoolElement.radioButtonRecyclerElementSchool.isChecked =
                schools[position].id == chosenSchool.id
            if (holder.bindingSchoolElement.radioButtonRecyclerElementSchool.isChecked) {
                checkedRadioButton = holder.bindingSchoolElement.radioButtonRecyclerElementSchool
                chosenSchool = schools[position]
            }
            holder.bindingSchoolElement.radioButtonRecyclerElementSchool.setOnCheckedChangeListener { compoundButton, _ ->
                checkedRadioButton?.isChecked = false
                checkedRadioButton = compoundButton
                chosenSchool = schools[position]
            }
        }

        override fun getItemCount() = schools.size

        class SchoolJsonHolder(val bindingSchoolElement: RecyclerElementSchoolsBinding) :
            RecyclerView.ViewHolder(bindingSchoolElement.root)
    }
}