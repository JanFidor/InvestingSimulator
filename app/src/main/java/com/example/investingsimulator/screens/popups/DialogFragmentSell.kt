package com.example.investingsimulator.screens.popups

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.investingsimulator.models.stockModel.StockBought
import com.example.investingsimulator.models.stockModel.StockFavourite
import com.example.investingsimulator.models.stockModel.StockTemplate
import com.example.investingsimulator.screens.fragments.FragmentStockDetails
import com.example.investingsimulator.screens.viewModels.ViewModelBought
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToInt

class DialogFragmentSell(stock : StockTemplate, viewModel: ViewModelBought, frag: FragmentStockDetails) : DialogFragmentTrade(stock, viewModel, frag) {
    override val tradeType: String
        get() = "Sell"

    override val owned: Float
        get() = viewModelBought.getAmount(stock.symbol)

    override val symbol = stock.symbol

    // TODO Proper fund gathering

    override fun observeValue(value: String) {
        Log.d("trade", "sell, $value")
        val price = stock.last.value!!.toFloat().round(2)
        val amount = value.toFloat()

        val correctValue = min(owned, amount).round(5)
        val correctAmount = (correctValue * price)

        // TODO if no new day -> get last from last day

        transactionValue.postValue(correctValue.toString())
        transactionAmount.postValue(correctAmount)
    }

    override fun executeTrade() {
        viewModelBought.sell(stock, transactionAmount.value?.toFloat() ?: 0F)
        (sharedPrefs?.edit())?.let {
            it.putFloat("FUNDS", funds + (transactionValue.value?.toFloat() ?: 0F))
            it.apply()
        }
        this.dismiss()
    }
}