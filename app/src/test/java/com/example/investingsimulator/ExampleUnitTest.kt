package com.example.investingsimulator

import com.example.investingsimulator.retrofit.RetrofitInstance
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.observers.TestObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subscribers.TestSubscriber
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class UnitTestSymbolSearch {

    @Test
    fun search_goog(){
        val query = "goog"
        val data = listOf("GOOG", "GOOGLE")

        val subs = RetrofitInstance.getSearchedStocks(query)
            .subscribeOn(Schedulers.trampoline())
            .observeOn(Schedulers.trampoline())
            .map{it.map { it.symbol }}
            .subscribeBy ({}, {}, {assertTrue(it.containsAll(data) && data.containsAll(it)) })
            .dispose()
    }

    @Test
    fun search_google(){
        val query = "google"
        val data = listOf("GOOGLE")

        val subs = RetrofitInstance.getSearchedStocks(query)
            .subscribeOn(Schedulers.trampoline())
            .observeOn(Schedulers.trampoline())
            .map{it.map {it.symbol}}
            .subscribeBy ({}, {}, {assertTrue(it.containsAll(data) && data.containsAll(it)) })
            .dispose()
    }


}