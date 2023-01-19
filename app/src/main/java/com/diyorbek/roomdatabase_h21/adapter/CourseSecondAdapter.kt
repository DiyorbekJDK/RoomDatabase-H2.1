package com.diyorbek.roomdatabase_h21.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.diyorbek.roomdatabase_h21.database.CourseEntity
import com.diyorbek.roomdatabase_h21.databinding.FirstItemLayoutBinding
import com.diyorbek.roomdatabase_h21.databinding.SecondItemLayoutBinding
import com.diyorbek.roomdatabase_h21.util.toBitmap

class CourseSecondAdapter :
    ListAdapter<CourseEntity, CourseSecondAdapter.CourseViewHolder>(DiffCallBack()) {
    lateinit var onClick: (CourseEntity) -> Unit
    lateinit var onEditClick: (CourseEntity) -> Unit
    lateinit var onDeleteClick: (CourseEntity) -> Unit

    private class DiffCallBack : DiffUtil.ItemCallback<CourseEntity>() {
        override fun areItemsTheSame(oldItem: CourseEntity, newItem: CourseEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CourseEntity, newItem: CourseEntity): Boolean {
            return oldItem == newItem
        }
    }

    inner class CourseViewHolder(private val binding: SecondItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(courseEntity: CourseEntity) {
            binding.img.setImageBitmap(courseEntity.img.toBitmap())
            binding.textName.text = courseEntity.courseName
            binding.btnEdit.setOnClickListener {
                onEditClick(courseEntity)
            }
            binding.btnDelete.setOnClickListener {
                onDeleteClick(courseEntity)
            }
            itemView.setOnClickListener {
                onClick(courseEntity)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        return CourseViewHolder(
            SecondItemLayoutBinding.inflate(
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