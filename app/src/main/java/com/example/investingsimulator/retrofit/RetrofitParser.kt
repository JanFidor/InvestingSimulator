package com.example.investingsimulator.retrofit

import android.util.Log
import retrofit2.Call
import retrofit2.Response


object RetrofitParser {
    fun getSymbols(wrapper: SymbolsWrapper2?)
    : List<SymbolData> =  wrapper?.let {it.securities.security}?.toList() ?: listOf()

    fun getSymbol(wrapper: SymbolWrapper2?)
    : List<SymbolData> =  wrapper?.let {listOf(it.securities.security)} ?: listOf()


    fun getHistory(wrapper: MarketHistoryMultiple?)
            : List<DayData> =  wrapper?.history?.day?.toList() ?: listOf()

    fun getQuote(wrapper: QuoteWrapper2?)
            : Quote? =  wrapper?.quotes?.quote
}