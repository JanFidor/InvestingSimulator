package com.example.investingsimulator

import android.util.Log
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.investingsimulator.models.stockModel.StockFavourite
import com.example.investingsimulator.retrofit.RetrofitInstance
import com.example.investingsimulator.room.favourite.StockFavouriteRoom
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
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
            .map{
                Log.d("search", "check 1")
                it.take(Integer.min(20, it.size))}

            // TODO make temporary cache
            .map {list -> Log.d("search", "check 2")
                list.map {
                        symbolData -> StockFavouriteRoom(symbolData.symbol, symbolData.description ?: "")
                }}
            .map { list -> Log.d("search", "check 3")
                list.map {stock ->
                    StockFavourite(stock)
                }}
            .subscribe({
                Log.d("search", "check 4")
            })
            .dispose()
    }

    @Test
    fun search_google(){
        val query = "googl"
        val data = listOf("GOOGLE")

        /*val subs = Observable.just(RetrofitInstance.getSearch(query))
            .subscribeOn(Schedulers.trampoline())
            .observeOn(Schedulers.trampoline())
            .map{
                Log.d("search", "check 1")
                it.take(Integer.min(20, it.size))}

            // TODO make temporary cache
            .map {list -> Log.d("search", "check 2")
                list.map {
                        symbolData -> StockFavouriteRoom(symbolData.symbol, symbolData.description ?: "")
                }}
            .map { list -> Log.d("search", "check 3")
                list.map {stock ->
                    StockFavourite(stock)
                }}
            .subscribe({
                Log.d("search", "check 4")
            })
            .dispose()
*/
    }
}