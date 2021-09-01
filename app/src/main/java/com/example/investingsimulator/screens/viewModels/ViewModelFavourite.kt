package com.example.investingsimulator.screens.viewModels

import android.app.Application
import com.example.investingsimulator.models.stockModel.StockFavourite
import com.example.investingsimulator.retrofit.RetrofitInstance
import com.example.investingsimulator.room.favourite.RepositoryFavouriteRoom
import com.example.investingsimulator.room.favourite.StockFavouriteRoom
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*
import java.util.concurrent.TimeUnit

class ViewModelFavourite(application: Application) : ViewModelTemplate<StockFavouriteRoom>(application) {
    override val _repository = RepositoryFavouriteRoom(application)


    val disposable = CompositeDisposable()

    fun showSearched(){
        val observable = Observable
            .just(searched.value ?: "")
            .debounce(300, TimeUnit.MILLISECONDS)
            .filter {it.isNotEmpty()}
            .map { it.lowercase(Locale.getDefault()).trim() }
            .distinctUntilChanged()
            .switchMap { query ->  Observable
                .create<Array<String>>{ RetrofitInstance.getSearchedStocks(query) }
            }
            // TODO transfer responsibility for converting data
            .map { list -> list.map {symbol -> StockFavouriteRoom(symbol) } }
            .map {  list -> list.map {stock -> StockFavourite(stock) }}
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                _stockVisible.value = _stockVisible.value?.plus(it)
            }
        disposable.add(observable)
    }

    override fun filterStock() {
        super.filterStock()
        showSearched()
    }
}