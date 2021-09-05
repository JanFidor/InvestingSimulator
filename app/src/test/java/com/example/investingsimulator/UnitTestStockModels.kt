package com.example.investingsimulator

import android.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.observe
import com.example.investingsimulator.models.stockModel.StockBought
import com.example.investingsimulator.models.stockModel.StockFavourite
import com.example.investingsimulator.retrofit.RetrofitInstance
import com.example.investingsimulator.retrofit.SymbolData
import com.example.investingsimulator.room.favourite.StockFavouriteRoom
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test

class UnitTestStockModels {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Test
    fun search_goog(){
        val query = "goog"
        val symbolsCorrect = listOf("GOOG", "GOOGLE")

        val a = RetrofitInstance.getSearchedStocks(query)
            .subscribeOn(AndroidSchedulers.mainThread())
            .observeOn(AndroidSchedulers.mainThread())

            .map{
                val a = it.map { it.symbol }
                print(a)
                a
            }
            .subscribeBy {  }
    }

    @Test
    fun search_google(){
        val query = "google"
        val symbolsCorrect = listOf("GOOGLE")

        RetrofitInstance.getSearchedStocks(query)
            .subscribeOn(AndroidSchedulers.mainThread())
            .observeOn(AndroidSchedulers.mainThread())
            .map{it.map { it.symbol }}
            .subscribeBy {assertEquals(it.containsAll(symbolsCorrect), symbolsCorrect.containsAll(it))}

    }

}