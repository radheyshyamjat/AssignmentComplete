package com.task.demo.ui.viewmodel

import android.app.Application
import androidx.annotation.NonNull
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.task.demo.data.model.WrapperPrizes
import com.task.demo.data.repositries.NobelPrizeRepository

class NobelPrizeViewModel(@NonNull application: Application) :
    AndroidViewModel(application) {
    private var nobelPrizeRepository: NobelPrizeRepository = NobelPrizeRepository.getInstance()

    fun getNobelPrizes(): LiveData<WrapperPrizes> {
        return nobelPrizeRepository.getNobelPrizes()
    }
}