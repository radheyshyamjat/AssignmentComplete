package com.task.demo.ui.fragments


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.task.demo.R
import com.task.demo.configuration.App
import com.task.demo.configuration.BaseAPIHelper
import com.task.demo.data.model.PrizesList
import com.task.demo.data.model.WrapperPrizes
import com.task.demo.ui.activities.MainActivity
import com.task.demo.ui.adapter.NobelPrizeAdapter
import com.task.demo.utils.debug
import com.task.demo.utils.toastShort
import kotlinx.android.synthetic.main.fragment_nobel_prize.*

const val SELECT_CATEGORY_RESULT_CODE: Int = 201
const val SELECTED_CATEGORY: String = "extra_category"

class NobelPrizeFragment : Fragment(R.layout.fragment_nobel_prize) {
    val categoryList: MutableList<String> = ArrayList()
    //    private lateinit var nobelPrizeViewModel: NobelPrizeViewModel
    private var filterList: MutableList<PrizesList> = ArrayList()
    private var actualList: MutableList<PrizesList> = ArrayList()
    private var tempList: MutableList<PrizesList> = ArrayList()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NobelPrizeAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        adapter = NobelPrizeAdapter(actualList, object : NobelPrizeAdapter.Callback {
            override fun onClick(dto: PrizesList) {

            }
        })
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        App.getAPIHelper()
            .getNobelPrizes(object : BaseAPIHelper.OnRequestComplete<WrapperPrizes> {
                override fun onSuccess(any: WrapperPrizes) {
                    tempList.addAll(any.prizes)
                    if (actualList.size<=0) {
                        actualList.clear()
                        actualList.addAll(any.prizes)
                    }
                    adapter.notifyDataSetChanged()

                }

                override fun onFailure(errorMessage: String, errorCode: Int) {
                    toastShort(errorMessage)
                }
            })
        floating.setOnClickListener {
            if (requireActivity() is MainActivity) {
                categoryList.clear()
                for (item in tempList) {
                    if (!categoryList.contains(item.category))
                        categoryList.add(item.category)
                }
                val fragment = FilterFragment.newInstance(categoryList as ArrayList<String>)
                fragment.setTargetFragment(this, SELECT_CATEGORY_RESULT_CODE)
                (requireActivity() as MainActivity).loadFragmentChip(fragment)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        debug("Request code:$requestCode & Result code $resultCode")
        if (requireActivity() is MainActivity) {
            (requireActivity() as MainActivity).onBackPressed()
        }
        if (Activity.RESULT_OK == resultCode) {
            if (SELECT_CATEGORY_RESULT_CODE == requestCode) {
                if (data != null && data.hasExtra(SELECTED_CATEGORY)) {
                    val category= data.getStringExtra(SELECTED_CATEGORY)
                    filterList.clear()
                    for (item in tempList) {
                        if (item.category.equals(category, true)) {
                            filterList.add(item)
                        }
                    }
                    actualList.clear()
                    actualList.addAll(filterList)
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }
}
