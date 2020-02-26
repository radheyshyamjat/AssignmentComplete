package com.task.demo.data.retrofit

import com.task.demo.data.model.WrapperPrizes
import retrofit2.Call
import retrofit2.http.GET

interface APIService {

    @GET("http://api.nobelprize.org/v1/prize.json")
    fun getNobelPrizes(): Call<WrapperPrizes>
}