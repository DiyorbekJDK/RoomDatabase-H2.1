package com.diyorbek.roomdatabase_h21.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.diyorbek.roomdatabase_h21.R
import com.diyorbek.roomdatabase_h21.database.CourseEntity
import com.diyorbek.roomdatabase_h21.databinding.FirstItemLayoutBinding
import com.diyorbek.roomdatabase_h21.databinding.SecondItemLayoutBinding
import com.diyorbek.roomdatabase_h21.util.toBitmap

class StringSecondAdapter :
    ListAdapter<String, StringSecondAdapter.StringViewHolder>(DiffCallBack()) {
    lateinit var onClick: (String) -> Unit
    lateinit var onEditClick: (String) -> Unit
    lateinit var onDeleteClick: (String) -> Unit
    var img2: ByteArray? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StringViewHolder {
        return StringViewHolder(
            SecondItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: StringViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class DiffCallBack : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem.toString() == newItem.toString()
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

    inner class StringViewHolder(private val binding: SecondItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(string: String) {
            binding.img.setImageBitmap(img2?.toBitmap())
            binding.textName.text = string
            itemView.setOnClickListener {
            }
            binding.btnEdit.setOnClickListener {
                onEditClick(string)
            }
            binding.btnDelete.setOnClickListener {
                onDeleteClick(string)
            }
            itemView.setOnClickListener {
                onClick(string)
            }
        }


    }
}