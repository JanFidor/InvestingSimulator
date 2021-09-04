package com.example.investingsimulator

import com.example.investingsimulator.retrofit.RetrofitInstance
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.observers.TestObserver
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
        var data: List<String>
        runBlocking {data = RetrofitInstance.getSearch(query)}

        val symbolsCorrect = listOf("GOOG", "GOOGLE")
        print(data)
        assertEquals(data.containsAll(symbolsCorrect), symbolsCorrect.containsAll(data))
    }

    @Test
    fun search_aapl(){
        val query = "aapl"
        var data: List<String>
        runBlocking {data = RetrofitInstance.getSearch(query)}

        val symbolsCorrect = listOf("AAPL")
        print(data)
        assertEquals(data.containsAll(symbolsCorrect), symbolsCorrect.containsAll(data))
    }


}