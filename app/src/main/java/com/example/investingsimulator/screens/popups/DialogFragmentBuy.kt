package com.example.investingsimulator.screens.popups

import androidx.lifecycle.MutableLiveData
import com.example.investingsimulator.models.stockModel.StockBought
import com.example.investingsimulator.models.stockModel.StockTemplate
import com.example.investingsimulator.screens.fragments.FragmentStockDetails
import com.example.investingsimulator.screens.viewModels.ViewModelBought
import kotlin.math.min

class DialogFragmentBuy(stock : StockTemplate, viewModel: ViewModelBought, frag: FragmentStockDetails) : DialogFragmentTrade(stock, viewModel, frag) {
    override val tradeType: String
        get() = "BUY"

    override val _owned: Float
        get() = viewModelBought.getAmount(stock.symbol)

    override val owned: String
        get() = _owned.toString()

    override val symbol = stock.symbol

    // TODO Proper fund gathering

    override fun observeAmount(_amount: String) {
        val amount = _amount.toFloat()

        val correctValue = min(_funds, amount * stock.last.value!!.toFloat())
        val correctAmount = correctValue / stock.last.value!!.toFloat()
        // TODO if no new day -> get last from last day

        transactionValue.postValue(correctValue.toString())
        transactionAmount.postValue(correctAmount.toString())
    }

    override fun observeValue(_value: String) {
        val value = _value.toFloat()

        val correctValue = min(_funds, value)
        val correctAmount = correctValue / stock.last.value!!.toFloat()
        // TODO if no new day -> get last from last day

        transactionValue.postValue(correctValue.toString())
        transactionAmount.postValue(correctAmount.toString())
    }

    override fun executeTrade() {
        viewModelBought.buy(stock, transactionAmount.value?.toFloat() ?: 0F)
        (sharedPrefs?.edit())?.let {
            it.putFloat("FUNDS", _funds - (transactionValue.value?.toFloat() ?: 0F))
            it.apply()
        }
        this.dismiss()
    }
}