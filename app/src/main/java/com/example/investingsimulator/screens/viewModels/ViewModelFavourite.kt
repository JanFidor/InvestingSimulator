package com.example.investingsimulator.screens.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.investingsimulator.models.stockModel.StockBought
import com.example.investingsimulator.models.stockModel.StockFavourite
import com.example.investingsimulator.models.stockModel.StockTemplate
import com.example.investingsimulator.retrofit.RetrofitInstance
import com.example.investingsimulator.room.favourite.RepositoryFavouriteRoom
import com.example.investingsimulator.room.favourite.StockFavouriteRoom
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.rx3.rxSingle
import java.lang.IllegalStateException
import java.util.*
import java.util.concurrent.TimeUnit

class ViewModelFavourite(application: Application) : ViewModelTemplate<StockFavouriteRoom>(application) {
    override val _repository = RepositoryFavouriteRoom(application)
    /*override val stockAll: MutableLiveData<List<StockTemplate>> =
        MutableLiveData(_repository.getAll().map{StockFavourite(it)})*/

    val disposable = CompositeDisposable()

    fun showSearched(){
        Log.d("search", "api call")

        val observable = Observable
            .just(searched.value ?: "")
            .debounce(300, TimeUnit.MILLISECONDS)
            .filter {it.isNotEmpty()}
            .map { it.lowercase(Locale.getDefault()).trim() }
            .distinctUntilChanged()
            /*.switchMap { query ->  Observable
                .create<Array<String>>{ RetrofitInstance.getSearchedStocks(query) }
            }*/
            /*.map { query ->
                RetrofitInstance.getSearch(query)
            }*/
            .flatMap {
                rxSingle { RetrofitInstance.getSearch(it)
                }.toObservable()
            }
            // TODO transfer responsibility for converting data
            .map {list -> list.map {symbolData ->
                StockFavouriteRoom(symbolData.symbol, symbolData.description)}}
            .map { list -> list.map {stock ->
                StockFavourite(stock)}}
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d("search", "list:   $it")
                _stockVisible.value = _stockVisible.value?.plus(it)
            }
        disposable.add(observable)
    }

    override fun filterStock() {
        super.filterStock()
        showSearched()
    }
}