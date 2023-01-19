package com.diyorbek.roomdatabase_h21.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.diyorbek.roomdatabase_h21.R
import com.diyorbek.roomdatabase_h21.adapter.CourseSecondAdapter
import com.diyorbek.roomdatabase_h21.adapter.StringAdapter
import com.diyorbek.roomdatabase_h21.adapter.StringSecondAdapter
import com.diyorbek.roomdatabase_h21.database.CourseDatabase
import com.diyorbek.roomdatabase_h21.database.CourseEntity
import com.diyorbek.roomdatabase_h21.databinding.FragmentAddCourseBinding
import com.diyorbek.roomdatabase_h21.databinding.FragmentAddModuleBinding
import com.diyorbek.roomdatabase_h21.util.toBitmap
import com.diyorbek.roomdatabase_h21.util.toByteArray
import com.google.android.material.snackbar.Snackbar


class AddModuleFragment : Fragment(R.layout.fragment_add_module) {
    private var _binding: FragmentAddModuleBinding? = null
    private val binding get() = _binding!!
    private val courseDatabase by lazy { CourseDatabase(requireContext()) }
    private val moduleAdapter by lazy { StringSecondAdapter() }
    private var list = ArrayList<String>()
    private var counter = 0
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAddModuleBinding.bind(view)
        mainCode()
    }

    private fun mainCode() {
        val img = arguments?.getByteArray("image")
        val name = arguments?.getString("name")
        binding.courseImg.setImageBitmap(img?.toBitmap())
        binding.textName.text = name

        binding.floatingActionButton3.setOnClickListener {
            if (binding.moduleName.text?.isNotBlank()!!) {
                list = ArrayList<String>()
                list.add(binding.moduleName.text.toString().trim())
                binding.moduleName.text?.clear()
                counter++
                binding.moduelsAdded.text = counter.toString()
                Snackbar.make(requireView(), "Module added Successfully", Snackbar.LENGTH_SHORT)
                    .show()
            } else {
                Snackbar.make(requireView(), "Enter name!", Snackbar.LENGTH_SHORT).show()
            }


        }
        binding.saveCourse.setOnClickListener {
            courseDatabase.dao.saveCourse(
                CourseEntity(
                    courseName = name!!,
                    img = img!!,
                    modules = list
                )
            )
            binding.courseImg.setImageResource(R.drawable.ic_baseline_image_24)
            //binding.textName.text = "Course Name"
            binding.textName.isVisible = false
            Snackbar.make(requireView(), "Course Saved Successfully", Snackbar.LENGTH_SHORT).show()
        }
        binding.recyclerView.apply {
            adapter = moduleAdapter
            layoutManager =
                LinearLayoutManager(requireContext())
        }
        moduleAdapter.img2 = binding.courseImg.toByteArray()
        moduleAdapter.submitList(list)
    }
}