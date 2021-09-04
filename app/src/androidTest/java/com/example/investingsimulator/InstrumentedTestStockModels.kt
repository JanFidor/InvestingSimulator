package com.example.investingsimulator

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.investingsimulator.models.stockModel.StockFavourite
import com.example.investingsimulator.room.favourite.StockFavouriteRoom
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class InstrumentedTestStockModels {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun test_1() {
        val symbol = "aapl"
        val stock = StockFavouriteRoom(symbol, "Apple Corporation")
        val model = StockFavourite(stock)

        runBlocking { model.getHistory() }

        Log.d("test", model._data.value?.dateData.toString())
    }
}