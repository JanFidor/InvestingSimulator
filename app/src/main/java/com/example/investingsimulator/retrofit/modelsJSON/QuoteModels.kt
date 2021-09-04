package com.example.investingsimulator.retrofit

import com.google.gson.annotations.SerializedName

data class QuoteWrapper2(
    @field:SerializedName("quotes") var quotes: QuoteWrapper1,
    )

data class QuoteWrapper1(
    @field:SerializedName("quote") var quote: Quote
)

data class Quote(
    @field:SerializedName("symbol") var symbol: String,
    @field:SerializedName("description") var description: String,
    /*@field:SerializedName("type") var type: String,*/
    @field:SerializedName("last") var last: Double,
    @field:SerializedName("change_percentage") var change_percentage: Double,
)
