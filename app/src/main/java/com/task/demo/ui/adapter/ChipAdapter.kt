package com.task.demo.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.task.demo.R
import kotlinx.android.synthetic.main.list_item_chip.view.*

class ChipAdapter(
    private var categoryList: List<String>, private var callback: Callback
) : RecyclerView.Adapter<ChipAdapter.ViewHolder>() {


    interface Callback {
        fun onClick(category: String)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_chip, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val order = categoryList[position]
        holder.bind(order)

        holder.itemView.chipCategory.setOnClickListener {
            callback.onClick(order)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var chipCategory: Chip = itemView.findViewById(R.id.chipCategory)

        @SuppressLint("SetTextI18n")
        fun bind(category: String) {
            chipCategory.text = category
        }
    }
}