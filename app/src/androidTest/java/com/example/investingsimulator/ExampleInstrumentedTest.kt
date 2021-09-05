package com.example.investingsimulator

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.investingsimulator.retrofit.RetrofitInstance
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.observers.TestObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import junit.framework.Assert

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.investingsimulator", appContext.packageName)
    }


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