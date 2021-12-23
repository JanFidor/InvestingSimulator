package com.example.investingsimulator.screens.popups

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.investingsimulator.R
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

    override fun getValueAndAmount(value: String): Pair<Float, Float> {
        val valueFloat = value.toFloat().round(2)
        val price = stock.last.value!!.toFloat()

        val correctValue = min(funds, valueFloat)
        val correctAmount = (correctValue / price)

        return Pair(correctValue, correctAmount)
    }

    override fun observeValue(value: String) {
        val (correctValue, correctAmount) = getValueAndAmount(value)

        transactionValue.postValue(correctValue.toString())
        transactionAmount.postValue(getString(R.string.text_share_amount, correctAmount))
    }

    override fun executeTrade() {
        val amount = (transactionValue.value?.toFloat() ?: 0F) / stock.last.value!!.toFloat()
        viewModelBought.buy(stock, amount.round(5))
        (sharedPrefs?.edit())?.let {
            it.putFloat("FUNDS", funds - (transactionValue.value?.toFloat() ?: 0F))
            it.apply()
        }
        this.dismiss()
    }
}

