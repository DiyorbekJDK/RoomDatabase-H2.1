package com.diyorbek.roomdatabase_h21.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.diyorbek.roomdatabase_h21.R
import com.diyorbek.roomdatabase_h21.adapter.CourseSecondAdapter
import com.diyorbek.roomdatabase_h21.adapter.StringSecondAdapter
import com.diyorbek.roomdatabase_h21.database.CourseDatabase
import com.diyorbek.roomdatabase_h21.database.CourseEntity
import com.diyorbek.roomdatabase_h21.databinding.FragmentAddCourseBinding
import com.diyorbek.roomdatabase_h21.databinding.FragmentDetailBinding
import com.diyorbek.roomdatabase_h21.util.toBitmap

class FragmentDetailFragment : Fragment(R.layout.fragment_detail) {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val courseDatabase by lazy { CourseDatabase(requireContext()) }
    private val courseAdapter by lazy { StringSecondAdapter() }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailBinding.bind(view)
        mainCode()
    }

    private fun mainCode() {
        binding.recyclerView.apply {
            adapter = courseAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        val arg = arguments?.getParcelable<CourseEntity>("course")
        courseAdapter.submitList(arg?.modules)
        binding.textView.text = arg?.courseName
    }
}