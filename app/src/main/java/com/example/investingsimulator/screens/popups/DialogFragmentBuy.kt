package com.example.investingsimulator.screens.popups

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.investingsimulator.models.stockModel.StockBought
import com.example.investingsimulator.models.stockModel.StockTemplate
import com.example.investingsimulator.screens.fragments.FragmentStockDetails
import com.example.investingsimulator.screens.viewModels.ViewModelBought
import java.lang.Math.round
import kotlin.math.min
import kotlin.math.roundToInt

class DialogFragmentBuy(stock : StockTemplate, viewModel: ViewModelBought, frag: FragmentStockDetails) : DialogFragmentTrade(stock, viewModel, frag) {
    override val tradeType: String
        get() = "BUY"

    override val owned: Float
        get() = viewModelBought.getAmount(stock.symbol)

    override val symbol = stock.symbol

    // TODO Proper fund gathering

    override fun observeValue(value: String) {
        Log.d("trade", "buy, value $value")
        val value = value.toFloat().round(2)
        val price = stock.last.value!!.toFloat()

        val correctValue = min(funds, value)
        val correctAmount = (correctValue / price)

        transactionValue.postValue(correctValue.toString())
        transactionAmount.postValue(correctAmount)
    }

    override fun executeTrade() {
        viewModelBought.buy(stock, transactionAmount.value?.toFloat() ?: 0F)
        (sharedPrefs?.edit())?.let {
            it.putFloat("FUNDS", funds - (transactionValue.value?.toFloat() ?: 0F))
            it.apply()
        }
        this.dismiss()
    }
}

