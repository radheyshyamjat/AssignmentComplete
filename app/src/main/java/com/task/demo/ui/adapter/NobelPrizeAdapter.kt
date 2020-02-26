package com.task.demo.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.task.demo.R
import com.task.demo.data.model.PrizesList

private val viewPool = RecyclerView.RecycledViewPool()

class NobelPrizeAdapter(
    private var userList: List<PrizesList>,
    private var callback: Callback
) : RecyclerView.Adapter<NobelPrizeAdapter.ViewHolder>() {


    interface Callback {
        fun onClick(dto: PrizesList)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_nobel_prize, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val order = userList[position]
        holder.bind(order)

        holder.itemView.setOnClickListener {
            callback.onClick(order)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var tvYear: TextView = itemView.findViewById(R.id.tvYear)
        private var tvCategory: TextView = itemView.findViewById(R.id.tvCategory)
        private var tvRecyclerView: RecyclerView = itemView.findViewById(R.id.recycler_view)
        private val childLayoutManager =
            LinearLayoutManager(tvRecyclerView.context, LinearLayoutManager.VERTICAL, false)

        @SuppressLint("SetTextI18n")
        fun bind(order: PrizesList) {
            tvYear.text = "${order.year?.capitalize()}"
            tvCategory.text = "${order.category?.capitalize()}"
            childLayoutManager.initialPrefetchItemCount = 4
            tvRecyclerView.apply {
                layoutManager = childLayoutManager
                adapter = ChildAdapter(order.laureates)
                setRecycledViewPool(viewPool)
            }
        }
    }
}