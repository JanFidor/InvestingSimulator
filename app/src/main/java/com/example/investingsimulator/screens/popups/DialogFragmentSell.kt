package com.example.investingsimulator.screens.popups

import androidx.lifecycle.MutableLiveData
import com.example.investingsimulator.models.stockModel.StockBought
import com.example.investingsimulator.models.stockModel.StockFavourite
import com.example.investingsimulator.models.stockModel.StockTemplate
import com.example.investingsimulator.screens.fragments.FragmentStockDetails
import com.example.investingsimulator.screens.viewModels.ViewModelBought
import kotlin.math.max
import kotlin.math.min

class DialogFragmentSell(stock : StockTemplate, viewModel: ViewModelBought, frag: FragmentStockDetails) : DialogFragmentTrade(stock, viewModel, frag) {
    override val tradeType: String
        get() = "Sell"

    override val _owned: Float
        get() = viewModelBought.getAmount(stock.symbol)

    override val owned: String
        get() = _owned.toString()

    override val symbol = stock.symbol

    // TODO Proper fund gathering

    override fun observeAmount(_amount: String) {
        val amount = _amount.toFloat()

        val correctAmount = if(amount <= _owned) amount else _owned
        val correctValue = correctAmount * stock.last.value!!.toFloat()
        // TODO if no new day -> get last from last day

        transactionValue.postValue(correctValue.toString())
        transactionAmount.postValue(correctAmount.toString())
    }

    override fun observeValue(_value: String) {
        val amount = _value.toFloat() / stock.last.value!!

        val correctAmount = if(amount <= _owned) amount.toFloat() else _owned
        val correctValue = correctAmount * stock.last.value!!.toFloat()
        // TODO if no new day -> get last from last day

        transactionValue.postValue(correctValue.toString())
        transactionAmount.postValue(correctAmount.toString())
    }

    override fun executeTrade() {
        viewModelBought.sell(stock, transactionAmount.value?.toFloat() ?: 0F)
        (sharedPrefs?.edit())?.let {
            it.putFloat("FUNDS", _funds + (transactionValue.value?.toFloat() ?: 0F))
            it.apply()
        }
        this.dismiss()
    }
}