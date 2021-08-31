package com.example.investingsimulator.retrofit.modelsJSON

import com.example.investingsimulator.retrofit.DayData

interface StockDataWrapper {
    val data: Array<DayData>
}

interface StockMarketData {
    val data: Array<DayData>
}