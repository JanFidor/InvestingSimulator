package com.example.investingsimulator.screens.viewModels

import android.app.Application
import android.util.Log
import com.example.investingsimulator.models.stockModel.StockFavourite
import com.example.investingsimulator.retrofit.RetrofitInstance
import com.example.investingsimulator.retrofit.modelsJSON.SymbolData
import com.example.investingsimulator.room.favourite.RepositoryFavouriteRoom
import com.example.investingsimulator.room.favourite.StockFavouriteRoom
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.lang.Integer.min

open class ViewModelFavourite(application: Application) : ViewModelTemplate<StockFavouriteRoom, StockFavourite>(application) {
    override val repository = RepositoryFavouriteRoom(application)

    override val stockAll: MutableMap<String, StockFavourite>
            by lazy {
                repository
                    .getAll()
                    .map{it.symbol to StockFavourite(it, true)}
                    .toMap()
                    .toMutableMap()
            }

    private val temporaryCache: MutableMap<String, StockFavourite> = mutableMapOf()

    private val compositeDisposable = CompositeDisposable()


    private fun Observable<List<SymbolData>>
            .processStockSymbolsToStockModels(): Observable<StockFavourite>{

        return this
            .flatMap {Observable.fromIterable(it)}
            .take(10)
            .filter{stockSymbol -> stockSymbol.symbol !in stockAll}
            .map{symbolData -> StockFavouriteRoom(
                symbolData.symbol, symbolData.description ?: "")}
            .map {stock -> StockFavourite(stock, false)}
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    private fun getStockSymbols(){
        val disposable = RetrofitInstance
            .getSearchedStocks(searched)
            .processStockSymbolsToStockModels()
            .subscribe(
            {stockFavourite ->
                if(stockFavourite.symbol !in temporaryCache) {
                    temporaryCache[stockFavourite.symbol] = stockFavourite
                }

                _stockVisible.postValue(_stockVisible.value?.plus(stockFavourite))},
            {getStockSymbol()}
        )

        compositeDisposable.add(disposable)
    }

    private fun getStockSymbol(){
        val disposable = RetrofitInstance
            .getSearchedStock(searched)
            .processStockSymbolsToStockModels()
            .subscribe(
            {_stockVisible.postValue(_stockVisible.value?.plus(it))},
            {e -> Log.e("Get single stock symbol", e.message.toString())}
        )
        compositeDisposable.add(disposable)
    }

    override fun filterSavedStocks() {
        val list = super.filterSavedStocks()
        if(searched != "") getStockSymbols()
    }

    override fun addStock(stock: StockFavouriteRoom) {
        repository.create(stock)
        val stockModel: StockFavourite? = temporaryCache[stock.symbol]

        stockModel?.let{
            stockAll[it.symbol] = it
            temporaryCache.remove(it.symbol)
        }
    }

    override fun deleteStock(stock: StockFavouriteRoom) {
        repository.delete(stock)
        val stockModel: StockFavourite? = stockAll[stock.symbol]

        stockModel?.let{
            temporaryCache[it.symbol] = it
            stockAll.remove(it.symbol)
        }
    }

    fun clear(){
        compositeDisposable.clear()
    }
}