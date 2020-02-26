package com.task.demo.data.model

class WrapperPrizes(var prizes: List<PrizesList>)

class PrizesList(var year: String, var category: String, val laureates: List<Laureates>)

class Laureates(
    var id: String, var firstname: String, val surname: String,
    val motivation: String, val share: String
)