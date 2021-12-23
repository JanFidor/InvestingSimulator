package com.example.investingsimulator.screens.popups

import com.example.investingsimulator.R
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

    override fun getValueAndAmount(value: String): Pair<Float, Float> {
        val price = stock.last.value!!.toFloat().round(2)
        val amount = value.toFloat()

        val correctValue = min(owned, amount).round(5)
        val correctAmount = (correctValue * price)

        return Pair(correctValue, correctAmount)
    }

    override fun observeValue(value: String) {
        val (correctValue, correctAmount) = getValueAndAmount(value)

        transactionValue.postValue(correctValue.toString())
        transactionAmount.postValue(getString(R.string.text_value_amount, correctAmount))
    }

    override fun executeTrade() {
        val value = (transactionValue.value?.toFloat() ?: 0F) * stock.last.value!!.toFloat()
        viewModelBought.sell(stock, transactionValue.value?.toFloat() ?: 0F)
        (sharedPrefs?.edit())?.let {
            it.putFloat("FUNDS", funds + value)
            it.apply()
        }
        this.dismiss()
    }
}