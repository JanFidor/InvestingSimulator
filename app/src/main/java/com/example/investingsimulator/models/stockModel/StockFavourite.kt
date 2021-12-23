package com.example.investingsimulator.models.stockModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.investingsimulator.room.favourite.StockFavouriteRoom


class StockFavourite (override val stockData: StockFavouriteRoom, observe: Boolean): StockTemplate(stockData) {
    private val _observed = MutableLiveData(observe)
    val observed: LiveData<Boolean>
        get() = _observed

    fun changeObservedStatus(){
        _observed.postValue(_observed.value?.not() ?: true)
    }

    fun getCore() = stockData

    override val symbol: String
        get() = stockData.symbol
    override val description: String
        get() = stockData.description
}