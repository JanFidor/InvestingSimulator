package com.example.investingsimulator.API_Fuckery

import com.example.investingsimulator.API_Fuckery.modelsJSON.DayData
import com.google.gson.annotations.SerializedName

data class MarketHistoryMultiple(
    @field:SerializedName("history") var history: DaysWrapper,
)

data class DaysWrapper(
    @field:SerializedName("day") var day: Array<DayData>,
)
