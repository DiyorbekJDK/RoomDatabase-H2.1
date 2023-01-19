package com.diyorbek.roomdatabase_h21.fragments

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.diyorbek.roomdatabase_h21.R
import com.diyorbek.roomdatabase_h21.adapter.CourseAdapter
import com.diyorbek.roomdatabase_h21.adapter.CourseSecondAdapter
import com.diyorbek.roomdatabase_h21.database.CourseDatabase
import com.diyorbek.roomdatabase_h21.database.CourseEntity
import com.diyorbek.roomdatabase_h21.databinding.FragmentAddCourseBinding
import com.diyorbek.roomdatabase_h21.databinding.FragmentCourseListBinding
import com.diyorbek.roomdatabase_h21.util.toByteArray
import com.google.android.material.snackbar.Snackbar

class AddCourseFragment : Fragment(R.layout.fragment_add_course) {
    private var _binding: FragmentAddCourseBinding? = null
    private val binding get() = _binding!!
    private val courseDatabase by lazy { CourseDatabase(requireContext()) }
    private val courseAdapter by lazy { CourseSecondAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAddCourseBinding.bind(view)
        mainCode()
    }

    private val imageLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) {
        it?.let { uri ->
            binding.imageView.setImageURI(uri)
        }
    }

    private fun mainCode() {
        binding.imageView.setOnClickListener {
            imageLauncher.launch("image/*")
        }
        binding.rv.apply {
            adapter = courseAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        courseAdapter.submitList(courseDatabase.dao.getAllCourses())
        binding.floatingActionButton2.setOnClickListener {
            if (binding.name.text.toString().isNotEmpty()) {
                val bundle = bundleOf()
                bundle.putByteArray("image", binding.imageView.toByteArray())
                bundle.putString("name", binding.name.text.toString().trim())
                findNavController().navigate(
                    R.id.action_addCourseFragment_to_addModuleFragment,
                    bundle
                )
            } else {
                Snackbar.make(requireView(), "Enter a Name!", Snackbar.LENGTH_SHORT)
                    .show()
            }

        }
//        binding.floatingActionButton2.setOnClickListener {
//            courseDatabase.dao.saveCourse(
//                CourseEntity(
//                    courseName = binding.name.text.toString().trim(),
//                    img = binding.imageView.toByteArray()
//                )
//            )
//            Snackbar.make(requireView(), "Saved Successfully", Snackbar.LENGTH_SHORT).show()
//            binding.imageView.setImageResource(R.drawable.ic_baseline_image_24)
//            binding.name.text?.clear()
//        }
        courseAdapter.onDeleteClick = { cour ->
            AlertDialog.Builder(requireContext()).apply {
                setTitle("Delete course")
                setMessage("Are you sure that you want to delete this course? There are lessons and modules here.")
                setNegativeButton("No") { d, _ ->
                    d.dismiss()
                }
                setPositiveButton("Yes") { b, _ ->
                    courseDatabase.dao.deleteCourse(cour)
                    Snackbar.make(requireView(), "Successfully deleted", Snackbar.LENGTH_SHORT)
                        .show()
                }
            }.create().show()
        }
        courseAdapter.onClick = {
            val bundle = bundleOf("course" to it)
            findNavController().navigate(
                R.id.action_addCourseFragment_to_fragmentDetailFragment,
                bundle
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}