package com.example.investingsimulator.models

import com.example.investingsimulator.API_Fuckery.modelsJSON.DayData
import com.github.mikephil.charting.data.CandleEntry
import org.nield.kotlinstatistics.median
import org.nield.kotlinstatistics.standardDeviation
import org.nield.kotlinstatistics.standardDeviationBy
import kotlin.math.abs
import kotlin.math.round

class StockAnalysis private constructor(history: Array<DayData>) {
    var volatility: List<String>
    var deviation: Double
    val candleData: MutableList<CandleEntry> = mutableListOf()
    var averageDelta: Float
    var dailyVolatility: Float

    init {
        var index = 0.0F
        history
            .map{
                CandleEntry(index, it.high.toFloat(), it.low.toFloat(), it.open.toFloat(), it.close.toFloat())
            }
            .forEach{
                candleData.add(it)
                index += 1.0F
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
        fun factory(history: Array<DayData>): StockAnalysis{
            return StockAnalysis(history)
        }
    }




}