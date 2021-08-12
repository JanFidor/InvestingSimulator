package com.example.investingsimulator.API_Fuckery

import com.google.gson.annotations.SerializedName

data class Model(
    @field:SerializedName("quotes") var quotes: Model2,
    )

data class Model2(
    @field:SerializedName("quote") var quote: Model3
)

data class Model3(
    @field:SerializedName("symbol") var symbol: String,
    @field:SerializedName("description") var description: String,
    @field:SerializedName("exch") var exch: String,
    @field:SerializedName("type") var type: String,
    @field:SerializedName("last") var last: String,
    @field:SerializedName("change_percentage") var change_percentage: String,
)
