package com.task.demo.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.task.demo.R
import com.task.demo.data.model.Laureates
import kotlinx.android.synthetic.main.list_item_child_nobel_prize.view.*

class ChildAdapter(private val children: List<Laureates>) :
    RecyclerView.Adapter<ChildAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_child_nobel_prize, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return children.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val child = children[position]
        holder.tvName.text = String.format("%s %s", child.firstname, child.surname)
        holder.tvMotivation.text = child.motivation
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.tvName
        val tvMotivation: TextView = itemView.tvMotivation
    }
}