package com.example.investingsimulator

import android.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.investingsimulator.models.StockAnalysis
import com.example.investingsimulator.retrofit.DayData
import com.example.investingsimulator.retrofit.RetrofitInstance
import com.example.investingsimulator.retrofit.RetrofitParser
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith


class UnitTestHistory {



    @get:Rule
    val rule = InstantTaskExecutorRule()



    /*@Test
    fun history_aapl_1() {
        val query = "aapl"
        val start = "2021-05-09"
        val end = "2021-06-09"

        var list: List<DayData>

        runBlocking {list =  RetrofitInstance.getHistory(query, start, end) }

        for(candle in list){
            println(candle)
        }

        assertEquals(list, correct)
    }*/


    val correct = listOf(
        DayData("2021-05-10", 129.41, 129.54, 126.81, 126.85, volume=88071229),
        DayData("2021-05-11", 123.5, high=126.27, low=122.77, close=125.91, volume=126142826),
        DayData(date="2021-05-12", open=123.4, high=124.64, low=122.25, close=122.77, volume=112172282),
        DayData(date="2021-05-13", open=124.58, high=126.15, low=124.26, close=124.97, volume=105861339),
        DayData(date="2021-05-14", open=126.25, high=127.89, low=125.85, close=127.45, volume=81917951),
        DayData(date="2021-05-17", open=126.82, high=126.93, low=125.17, close=126.27, volume=74244624),
        DayData(date="2021-05-18", open=126.56, high=126.99, low=124.78, close=124.85, volume=63342929),
        DayData(date="2021-05-19", open=123.16, high=124.915, low=122.86, close=124.69, volume=92611989),
        DayData(date="2021-05-20", open=125.23, high=127.72, low=125.1, close=127.31, volume=76857123),
        DayData(date="2021-05-21", open=127.82, high=128.0, low=125.21, close=125.43, volume=79295436),
        DayData(date="2021-05-24", open=126.01, high=127.94, low=125.94, close=127.1, volume=63092945),
        DayData(date="2021-05-25", open=127.82, high=128.32, low=126.32, close=126.9, volume=72009482),
        DayData(date="2021-05-26", open=126.955, high=127.39, low=126.42, close=126.85, volume=56575920),
        DayData(date="2021-05-27", open=126.44, high=127.64, low=125.08, close=125.28, volume=94625601),
        DayData(date="2021-05-28", open=125.57, high=125.8, low=124.55, close=124.61, volume=71311109),
        DayData(date="2021-06-01", open=125.08, high=125.35, low=123.94, close=124.28, volume=67637118),
        DayData(date="2021-06-02", open=124.28, high=125.24, low=124.05, close=125.06, volume=59278862),
        DayData(date="2021-06-03", open=124.68, high=124.85, low=123.13, close=123.54, volume=76229170),
        DayData(date="2021-06-04", open=124.07, high=126.16, low=123.85, close=125.89, volume=75169343),
        DayData(date="2021-06-07", open=126.17, high=126.32, low=124.8321, close=125.9, volume=71057550),
        DayData(date="2021-06-08", open=126.6, high=128.46, low=126.2101, close=126.74, volume=74403774),
        DayData(date="2021-06-09", open=127.21, high=127.75, low=126.52, close=127.13, volume=56877937),
    )
}