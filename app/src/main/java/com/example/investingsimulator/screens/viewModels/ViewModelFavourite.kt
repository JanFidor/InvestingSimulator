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
                val map = mutableMapOf<String, StockFavourite>()
                repository.getAll().forEach {
                    Log.d("stock", it.toString())
                    map[it.symbol] = StockFavourite(it, true)
                }
                map}

    val temporaryCache: MutableMap<String, StockFavourite> = mutableMapOf()

    private val disposable = CompositeDisposable()

    fun parse(obs: Observable<List<SymbolData>>): Observable<List<StockFavourite>> {
        return obs
            .map { it.filter { it.symbol !in stockAll } }
            .map{
                it.take(min(20, it.size))}
            .map {list ->
                list.map {
                        symbolData -> StockFavouriteRoom(symbolData.symbol, symbolData.description ?: "")}}
            .map { list -> list.map {stock ->
                if(stock.symbol !in temporaryCache) temporaryCache[stock.symbol] = StockFavourite(stock, false)
                temporaryCache[stock.symbol]!!}}
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    private fun getObservables(list: List<StockFavourite>){
        val obs = RetrofitInstance.getSearchedStocks(searched)
        val dis = parse(obs).subscribe (
            { Log.d("call", "1   $list   $it")
                _stockVisible.postValue(list.plus(it))
            }, {getObservable(list)})

        disposable.add(dis)
    }

    private fun getObservable(list: List<StockFavourite>){
        val obs = RetrofitInstance.getSearchedStock(searched)
        val dis = parse(obs).subscribe(
            { Log.d("call", "2  $list   $it")
                _stockVisible.postValue(list.plus(it)) }, {})
        disposable.add(dis)
    }

    override fun filterStock(): List<StockFavourite> {

        val list = super.filterStock()
        Log.d("call", "list: $list")
        if(searched != "") getObservables(list)
        return list
    }

    override fun add(stock: StockFavouriteRoom) {
        repository.create(stock)
        val stock: StockFavourite? = temporaryCache[stock.symbol]
        stock?.let{
            stockAll[it.symbol] = it
            temporaryCache.remove(it.symbol)
        }

    }

    override fun delete(stock: StockFavouriteRoom) {
        repository.delete(stock)
        val stock: StockFavourite? = stockAll[stock.symbol]
        stock?.let{
            temporaryCache[it.symbol] = it
            stockAll.remove(it.symbol)
        }
    }

    fun clear(){
        disposable.clear()
    }
}