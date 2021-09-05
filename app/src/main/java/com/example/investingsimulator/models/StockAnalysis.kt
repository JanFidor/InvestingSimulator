package com.example.investingsimulator.models

import android.util.Log
import com.example.investingsimulator.retrofit.DayData
import com.github.mikephil.charting.data.CandleEntry
import org.nield.kotlinstatistics.standardDeviation
import kotlin.math.abs
import kotlin.math.round

class StockAnalysis(history: List<DayData>) {
    var volatility: List<String>
    var deviation: Double
    val candleData: MutableList<CandleEntry> = mutableListOf()
    val dateData: List<String> = history.map{it.date.slice(5..9)} ?: listOf()
    val volumeData: List<Long> = history.map{it.volume} ?: listOf()

    var averageDelta: Float
    var dailyVolatility: Float



    init {
        history
        .mapIndexed { index, dayData ->
            CandleEntry(
                index.toFloat(),
                dayData.high.toFloat(), dayData.low.toFloat(),
                dayData.open.toFloat(), dayData.close.toFloat()
            )
        }
        .forEach{
            candleData.add(it)
        }

        val averagePrice = candleData.map{(it.open + it.close) / 2}
        deviation = averagePrice.standardDeviation()
        val mean = averagePrice.average()
        val devs = mutableListOf(0.0, 0.0, 0.0)
        for(v in averagePrice) {
            for (i in 1..3) {
                if ((mean - i * deviation) <= v && v <= (mean + i * deviation)) {
                    devs[i - 1] += 1.0
                }
            }
        }
        volatility = devs.map{"${round(it/history.size * 100)}%"}

        averageDelta = candleData.map {abs(it.open - it.close) / (it.open + it.close)}.sum() / candleData.size
        dailyVolatility = candleData.map {it.high - it.low / (it.open + it.close)}.sum() / candleData.size
    }

    companion object{
        /*fun factory(history: Array<DayData>, stocks: String): StockAnalysis{
            return StockAnalysis(history, stocks)
        }*/
    }




}