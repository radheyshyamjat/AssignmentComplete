package com.task.demo.ui.fragments


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.task.demo.R
import com.task.demo.ui.adapter.ChipAdapter

private const val ARG_PARAM1 = "list"

class FilterFragment : Fragment(R.layout.fragment_filter) {
    private var categoryList: ArrayList<String> = ArrayList()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ChipAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            if (it.containsKey(ARG_PARAM1)) categoryList =
                it.getStringArrayList(ARG_PARAM1) as ArrayList<String>
        }
        recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        adapter = ChipAdapter(categoryList, object : ChipAdapter.Callback {
            override fun onClick(category: String) {
                val intent = Intent()
                intent.putExtra(SELECTED_CATEGORY, category)
                targetFragment?.onActivityResult(
                    SELECT_CATEGORY_RESULT_CODE,
                    Activity.RESULT_OK,
                    intent
                )
            }
        })
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)

    }

    companion object {
        @JvmStatic
        fun newInstance(year: ArrayList<String>) =
            FilterFragment().apply {
                arguments = Bundle().apply {
                    putStringArrayList(ARG_PARAM1, year)
                }
            }
    }
}
