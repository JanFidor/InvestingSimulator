package com.example.investingsimulator.retrofit

import com.example.investingsimulator.retrofit.modelsJSON.StockDataWrapper
import com.example.investingsimulator.retrofit.modelsJSON.StockMarketData
import com.google.gson.annotations.SerializedName

data class DayData(
    @field:SerializedName("date") var date: String,
    @field:SerializedName("open") var open: Double,
    @field:SerializedName("high") var high: Double,
    @field:SerializedName("low") var low: Double,
    @field:SerializedName("close") var close: Double,
    @field:SerializedName("volume") var volume: Long,
)

data class MarketHistorySingle(
    @field:SerializedName("history") var history: DayWrapper,
) : StockMarketData {
    override val data = history.data
}

data class DayWrapper(
    @field:SerializedName("day") var day: DayData,
): StockDataWrapper{
    override val data = arrayOf(day)
}

data class MarketHistoryMultiple(
    @field:SerializedName("history") var history: DaysWrapper,
) : StockMarketData{
    override val data = history.data
}

data class DaysWrapper(
    @field:SerializedName("day") var day: Array<DayData>,
): StockDataWrapper{
    override val data = day
}
