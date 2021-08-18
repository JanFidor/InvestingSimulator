package com.example.investingsimulator.API_Fuckery.modelsJSON

import com.google.gson.annotations.SerializedName

data class DayData(
    @field:SerializedName("date") var date: String,
    @field:SerializedName("open") var open: Double,
    @field:SerializedName("high") var high: Double,
    @field:SerializedName("low") var low: Double,
    @field:SerializedName("close") var close: Double,
    @field:SerializedName("volume") var volume: Double,
)
