package com.example.investingsimulator.retrofit.modelsJSON

data class DayData(
    val date: String,
    val open: Float?,
    val high: Float?,
    val low: Float?,
    val close: Float?,
    val volume: Long?,
)

data class MarketHistoryMultiple(
    val history: DaysWrapper,
)

data class DaysWrapper(
    val day: Array<DayData>,
)
