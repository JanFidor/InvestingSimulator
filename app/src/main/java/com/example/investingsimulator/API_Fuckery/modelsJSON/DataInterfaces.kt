package com.example.investingsimulator.API_Fuckery.modelsJSON

import com.example.investingsimulator.API_Fuckery.DayData

interface StockDataWrapper {
    val data: Array<DayData>
}

interface StockMarketData {
    val data: Array<DayData>
}