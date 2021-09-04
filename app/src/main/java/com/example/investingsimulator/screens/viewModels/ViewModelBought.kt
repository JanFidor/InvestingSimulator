package com.example.investingsimulator.screens.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.investingsimulator.models.stockModel.StockBought
import com.example.investingsimulator.models.stockModel.StockFavourite
import com.example.investingsimulator.models.stockModel.StockTemplate
import com.example.investingsimulator.retrofit.RetrofitInstance
import com.example.investingsimulator.room.bought.RepositoryBoughtRoom
import com.example.investingsimulator.room.bought.StockBoughtRoom
import com.example.investingsimulator.room.favourite.RepositoryFavouriteRoom
import com.example.investingsimulator.room.favourite.StockFavouriteRoom
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*
import java.util.concurrent.TimeUnit

class ViewModelBought(application: Application) : ViewModelTemplate<StockBoughtRoom>(application) {
    override val _repository = RepositoryBoughtRoom(application)
    /*override val stockAll: MutableLiveData<List<StockTemplate>> =
        MutableLiveData(_repository.getAll().map{StockFavourite(it)})*/

}