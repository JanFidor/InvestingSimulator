package com.example.investingsimulator

import android.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.observe
import com.example.investingsimulator.models.stockModel.StockBought
import com.example.investingsimulator.models.stockModel.StockFavourite
import com.example.investingsimulator.room.favourite.StockFavouriteRoom
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test

class UnitTestStockModels {
    @get:Rule
    val rule = InstantTaskExecutorRule()
}