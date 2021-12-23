package com.example.investingsimulator.retrofit.modelsJSON


data class QuoteWrapper2(
    val quotes: QuoteWrapper1,
    )

data class QuoteWrapper1(
    val quote: Quote
)

data class Quote(
    val symbol: String,
    val description: String?,
    val last: Float?,
    val change_percentage: Float?,
)
