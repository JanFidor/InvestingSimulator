package com.example.investingsimulator

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.investingsimulator.retrofit.RetrofitInstance
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import junit.framework.Assert.assertEquals
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