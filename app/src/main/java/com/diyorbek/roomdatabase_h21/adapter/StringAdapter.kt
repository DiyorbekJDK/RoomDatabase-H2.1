package com.diyorbek.roomdatabase_h21.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.diyorbek.roomdatabase_h21.database.CourseEntity
import com.diyorbek.roomdatabase_h21.databinding.FirstItemLayoutBinding
import com.diyorbek.roomdatabase_h21.databinding.ModuleLayoutBinding

class StringAdapter : ListAdapter<String, StringAdapter.CourseViewHolder>(DiffCallBack()) {
    lateinit var onClick: (String) -> Unit

    private class DiffCallBack : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem.toString() == newItem.toString()
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

    inner class CourseViewHolder(private val binding: ModuleLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(string: String) {
            binding.name.text = string
            itemView.setOnClickListener {

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        return CourseViewHolder(
            ModuleLayoutBinding.inflate(
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