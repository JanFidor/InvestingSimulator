package com.example.investingsimulator.retrofit

import com.example.investingsimulator.retrofit.modelsJSON.*


object RetrofitParser {
    fun getSymbols(wrapper: SymbolsWrapper2?)
    : List<SymbolData> =  wrapper?.let {it.securities.security}?.toList() ?: listOf()

    fun getSymbol(wrapper: SymbolWrapper2?)
    : List<SymbolData> =  wrapper?.let {listOf(it.securities.security)} ?: listOf()

    fun getHistory(wrapper: MarketHistoryMultiple?)
            : List<DayData> =  wrapper?.history?.day?.toList() ?: listOf()
}