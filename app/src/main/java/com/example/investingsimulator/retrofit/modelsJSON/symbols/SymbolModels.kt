package com.example.investingsimulator.retrofit

import com.example.investingsimulator.retrofit.modelsJSON.StockDataWrapper
import com.example.investingsimulator.retrofit.modelsJSON.StockMarketData
import com.example.investingsimulator.retrofit.modelsJSON.SymbolWrapperInterface1
import com.example.investingsimulator.retrofit.modelsJSON.SymbolWrapperInterface2
import com.example.investingsimulator.room.templates.StockTemplateRoom
import com.google.gson.annotations.SerializedName


data class SymbolWrapper2(
    @field:SerializedName("securities") private var symbolWrapper: SymbolWrapper1,
)  : SymbolWrapperInterface1{
    override val data = symbolWrapper.data
}

data class SymbolWrapper1(
    @field:SerializedName("security") private var symbol: SymbolData,
) : SymbolWrapperInterface1{
    override val data = arrayOf(symbol.symbol)
}

data class SymbolsWrapper2(
    @field:SerializedName("securities") private var symbolWrapper: SymbolsWrapper1,
) : SymbolWrapperInterface2 {
    override val data = symbolWrapper.data
}

data class SymbolsWrapper1(
    @field:SerializedName("security") private var symbolList: Array<SymbolData>,
) : SymbolWrapperInterface1{
    override val data: Array<String> = symbolList.map{it.symbol}.toTypedArray()
}

data class SymbolData(
    @field:SerializedName("symbol") override val symbol: String,
) : StockTemplateRoom()

