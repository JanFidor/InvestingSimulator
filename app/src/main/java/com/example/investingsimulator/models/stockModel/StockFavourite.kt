package com.example.investingsimulator.models.stockModel

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.investingsimulator.models.TextFormatting
import com.example.investingsimulator.room.favourite.StockFavouriteRoom
import com.example.investingsimulator.room.templates.StockDB
import com.example.investingsimulator.room.templates.StockTemplateRoom
import com.example.investingsimulator.screens.viewModels.ViewModelFavourite
import com.example.investingsimulator.screens.viewModels.ViewModelTemplate

class StockFavourite (override val stockData: StockFavouriteRoom, observe: Boolean): StockTemplate(stockData) {

    protected val _observed = MutableLiveData(observe)
    val observed: LiveData<Boolean>
        get() = _observed

    fun changeObserved(){
        _observed.postValue(_observed.value?.not() ?: true)
    }

    fun getCore() = stockData

    override val symbol: String
        get() = stockData.symbol
    override val description: String
        get() = stockData.description

    /*override val stockData = stockData as StockFavouriteRoom*/
}