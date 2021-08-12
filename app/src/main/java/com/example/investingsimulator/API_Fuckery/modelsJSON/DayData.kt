package com.example.investingsimulator.API_Fuckery.modelsJSON

import com.google.gson.annotations.SerializedName

data class DayData(
    @field:SerializedName("date") var date: String,
    @field:SerializedName("open") var open: String,
    @field:SerializedName("high") var high: String,
    @field:SerializedName("low") var low: String,
    @field:SerializedName("close") var close: String,
    @field:SerializedName("volume") var volume: String,
)
