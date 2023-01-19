package com.diyorbek.roomdatabase_h21.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.diyorbek.roomdatabase_h21.R
import com.diyorbek.roomdatabase_h21.adapter.CourseAdapter
import com.diyorbek.roomdatabase_h21.database.CourseDatabase
import com.diyorbek.roomdatabase_h21.databinding.FragmentCourseListBinding

class CourseListFragment : Fragment(R.layout.fragment_course_list) {
    private var _binding: FragmentCourseListBinding? = null
    private val binding get() = _binding!!
    private val courseDatabase by lazy { CourseDatabase(requireContext()) }
    private val courseAdapter by lazy { CourseAdapter() }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCourseListBinding.bind(view)
        mainCode()
    }

    private fun mainCode() {
        binding.rv.apply {
            adapter = courseAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        courseAdapter.submitList(courseDatabase.dao.getAllCourses())
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_courseListFragment_to_addCourseFragment)
        }
        courseAdapter.onClick = {
            val bundle = bundleOf("course" to it)
            findNavController().navigate(
                R.id.action_courseListFragment_to_fragmentDetailFragment,
                bundle
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}