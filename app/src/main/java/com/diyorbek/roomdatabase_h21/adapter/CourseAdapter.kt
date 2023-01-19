package com.diyorbek.roomdatabase_h21.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.diyorbek.roomdatabase_h21.database.CourseEntity
import com.diyorbek.roomdatabase_h21.databinding.FirstItemLayoutBinding

class CourseAdapter : ListAdapter<CourseEntity, CourseAdapter.CourseViewHolder>(DiffCallBack()) {
    lateinit var onClick: (CourseEntity) -> Unit
    lateinit var contextt: Context

    private class DiffCallBack : DiffUtil.ItemCallback<CourseEntity>() {
        override fun areItemsTheSame(oldItem: CourseEntity, newItem: CourseEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CourseEntity, newItem: CourseEntity): Boolean {
            return oldItem == newItem
        }
    }

    inner class CourseViewHolder(private val binding: FirstItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(courseEntity: CourseEntity) {
            val adapte3 = StringAdapter()
            binding.courseName.text = courseEntity.courseName
            binding.rv.apply {
                adapter = adapte3
                layoutManager = LinearLayoutManager(contextt, LinearLayoutManager.HORIZONTAL, false)
            }
            adapte3.submitList(courseEntity.modules.toMutableList())
            itemView.setOnClickListener {
                onClick(courseEntity)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        contextt = parent.context
        return CourseViewHolder(
            FirstItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}