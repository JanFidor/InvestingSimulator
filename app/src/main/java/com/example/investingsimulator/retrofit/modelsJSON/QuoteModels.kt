package com.example.investingsimulator.retrofit

import com.google.gson.annotations.SerializedName

data class QuoteDataWrapper(
    @field:SerializedName("quotes") var quotes: DataShell,
    )

data class DataShell(
    @field:SerializedName("quote") var quote: Quote
)

data class Quote(
    @field:SerializedName("symbol") var symbol: String,
    @field:SerializedName("description") var description: String,
    @field:SerializedName("exch") var exch: String,
    /*@field:SerializedName("type") var type: String,*/
    @field:SerializedName("last") var last: Double,
    @field:SerializedName("change_percentage") var change_percentage: Double,
)
