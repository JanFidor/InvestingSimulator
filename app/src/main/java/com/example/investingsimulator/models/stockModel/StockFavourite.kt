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

class StockFavourite (stockData: StockTemplateRoom, observe: Boolean): StockTemplate(stockData, observe) {
    override val isSellable: Boolean
        get() = false




    fun changeObserved(view: ImageView){
        val current = _observed.value
        current?.let {
            Log.d("wtf", "$symbol ${!current}")
            TextFormatting.setObservedColor(!current, view)

            val dao = StockDB.getInstance(view.context).stockFavouriteDAO
            if(current){
                Toast.makeText(view.context, "deleted $symbol from observed", Toast.LENGTH_SHORT).show()
                dao.delete(stockData as StockFavouriteRoom)
            }
            else{
                Toast.makeText(view.context, "added $symbol to observed", Toast.LENGTH_SHORT).show()
                dao.insert(stockData as StockFavouriteRoom)
            }

            Log.d("database", "${dao.getAll()}")
            _observed.postValue(!current)
        }




    }

    companion object{
        fun create(stockData: StockFavouriteRoom, observe: Boolean): StockFavourite{
            return StockFavourite(stockData, observe)
        }

        fun create(stockData: List<StockFavouriteRoom>, observe: List<Boolean>): List<StockFavourite>{
            return stockData.mapIndexed{ind, it -> StockFavourite(it, observe[ind])}
        }

    }

    /*override val stockData = stockData as StockFavouriteRoom*/
}