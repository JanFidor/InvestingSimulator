package com.example.investingsimulator.models

import com.example.investingsimulator.retrofit.modelsJSON.DayData
import com.github.mikephil.charting.data.CandleEntry


class StockAnalysis(history: List<DayData>) {
    val candleData = history.mapIndexed {index, dayData -> createCandleEntry(index, dayData)}
    val dateData: List<String> = history.map{it.date.slice(5..9)}
    val volumeData: List<Long> = history.map{it.volume ?: 0}

    private fun createCandleEntry(index: Int, dayData: DayData): CandleEntry{
        return CandleEntry(index.toFloat(),
            dayData.high ?: 1f, dayData.low ?: 1f,
            dayData.open ?: 1f, dayData.close ?: 1f
        )
    }

}