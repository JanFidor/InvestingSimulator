package com.example.investingsimulator.API_Fuckery

import com.example.investingsimulator.API_Fuckery.modelsJSON.DayData
import com.google.gson.annotations.SerializedName

data class MarketHistorySingle(
    @field:SerializedName("history") var history: DayWrapper,
)

data class DayWrapper(
    @field:SerializedName("day") var day: DayData,
)
