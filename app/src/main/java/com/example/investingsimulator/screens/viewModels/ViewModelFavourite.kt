package com.example.investingsimulator.screens.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.investingsimulator.models.stockModel.StockBought
import com.example.investingsimulator.models.stockModel.StockFavourite
import com.example.investingsimulator.models.stockModel.StockTemplate
import com.example.investingsimulator.retrofit.RetrofitInstance
import com.example.investingsimulator.retrofit.SymbolData
import com.example.investingsimulator.room.favourite.RepositoryFavouriteRoom
import com.example.investingsimulator.room.favourite.StockFavouriteRoom
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.rx3.rxSingle
import java.lang.IllegalStateException
import java.lang.Integer.min
import java.util.*
import java.util.concurrent.TimeUnit

class ViewModelFavourite(application: Application) : ViewModelTemplate<StockFavouriteRoom>(application) {
    override val _repository = RepositoryFavouriteRoom(application)
    /*override val stockAll: MutableLiveData<List<StockTemplate>> =
        MutableLiveData(_repository.getAll().map{StockFavourite(it)})*/

    private val disposable = CompositeDisposable()

    fun showSearched(){
        Log.d("search", "api call")

        observables()
    }
    fun parse(obs: Observable<List<SymbolData>>): Observable<List<StockFavourite>> {
        return obs.map{
            Log.d("search", "check 1")
            it.take(min(20, it.size))}

            // TODO make temporary cache
            .map {list -> Log.d("search", "check 2")
                list.map {
                        symbolData -> StockFavouriteRoom(symbolData.symbol, symbolData.description ?: "")}}
            .map { list -> Log.d("search", "check 3")
                list.map {stock ->
                    StockFavourite(stock)}}
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun observables(){
        val obs = RetrofitInstance.getSearchedStocks(searched)
        val dis = parse(obs).subscribe ({
            Log.d("search", "check 4")
            _stockVisible.value = _stockVisible.value?.plus(it)
        }, {observable()})
        disposable.add(dis)
    }

    fun observable(){
        val obs = RetrofitInstance.getSearchedStock(searched)
        val dis = parse(obs).subscribe ({
                Log.d("search", "check 4")
                _stockVisible.value = _stockVisible.value?.plus(it)
            }, {})
        disposable.add(dis)
    }

    override fun filterStock() {
        super.filterStock()
        showSearched()
    }
}