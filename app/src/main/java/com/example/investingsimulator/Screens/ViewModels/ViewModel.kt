package com.example.investingsimulator.Screens.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.investingsimulator.Room_Fuckery.templates.RoomRepository
import com.example.investingsimulator.Room_Fuckery.templates.StockTemplateRoom
import com.example.investingsimulator.models.stockModel.StockTemplate

class ViewModel(application: Application) : AndroidViewModel(application) {
    //private val _repository = RecipeRepository(application)

    private val _repository = RoomRepository<StockTemplateRoom>(application)

    // TODO make factory for parsing Room -> Model
    private val stockAll: List<StockTemplate> = listOf()
    private val _stockVisible: MutableLiveData<List<StockTemplate>> = MutableLiveData(stockAll)
    val stockVisible: LiveData<List<StockTemplate>>
        get() = _stockVisible

    private val searched: MutableLiveData<String> = MutableLiveData("")

    private fun filterStock() {
        _stockVisible.value =
            stockAll.filter {
                it.stockData.symbol.length >= (searched.value?.length ?: 0) &&
                it.stockData.symbol.slice(searched.value?.indices ?: 0..0)  == searched.value
            }
    }

    fun add(stock: StockTemplateRoom) {
        _repository.create(stock)
        filterStock()
    }
    fun deleteRecipe(stock: StockTemplateRoom) {
        _repository.delete(stock)
        filterStock()
    }
    fun updateSearch(search: String){
        searched.value = search
        filterStock()
    }
}