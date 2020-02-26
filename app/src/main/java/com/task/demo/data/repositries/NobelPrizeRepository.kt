package com.task.demo.data.repositries

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.task.demo.configuration.App
import com.task.demo.configuration.BaseAPIHelper
import com.task.demo.data.model.WrapperPrizes
import com.task.demo.utils.toastShort

class NobelPrizeRepository {
    private val prizeLiveData = MutableLiveData<WrapperPrizes>()

    companion object {
        private lateinit var instance: NobelPrizeRepository
        fun getInstance(): NobelPrizeRepository {
            if (!(Companion::instance.isInitialized)) instance =
                NobelPrizeRepository(); return instance
        }
    }

    fun getNobelPrizes(): LiveData<WrapperPrizes> {
        App.getAPIHelper()
            .getNobelPrizes(object : BaseAPIHelper.OnRequestComplete<WrapperPrizes> {
                override fun onSuccess(any: WrapperPrizes) {
                    prizeLiveData.postValue(any)
                }

                override fun onFailure(errorMessage: String, errorCode: Int) {
                    toastShort(errorMessage)
                }
            })
        return prizeLiveData
    }
}