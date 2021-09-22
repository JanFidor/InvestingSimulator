package com.example.investingsimulator.screens.popups

import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.MutableLiveData
import com.example.investingsimulator.databinding.FragmentListBoughtBinding
import com.example.investingsimulator.databinding.PopupTradeBinding
import com.example.investingsimulator.models.stockModel.StockTemplate
import com.example.investingsimulator.screens.fragments.FragmentStockDetails
import com.example.investingsimulator.screens.viewModels.ViewModelBought
import com.example.investingsimulator.screens.viewModels.ViewModelFavourite
import java.util.*

abstract class DialogFragmentTrade(val stock: StockTemplate, protected val viewModelBought: ViewModelBought, val frag: FragmentStockDetails)  : DialogFragment() {

    private lateinit var binding: FragmentListBoughtBinding

    abstract val tradeType: String
    abstract val symbol: String
    abstract val owned: Float

    protected val sharedPrefs = frag.activity?.getPreferences(Context.MODE_PRIVATE)

    val funds = sharedPrefs?.getFloat("FUNDS", 0.0F) ?: 0.0F
    val last = stock.last.value!!.toFloat()
    val change = stock.change.value?.toFloat() ?: 0f

    val transactionValue  =  MutableLiveData("0.0")
    val transactionAmount = MutableLiveData(0f)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {

        binding = FragmentListBoughtBinding.inflate(inflater, container, false)

        // Beautiful magic scroll that removes addition background for window UI
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        transactionValue.observe(viewLifecycleOwner, ::observeValue)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
        }
        binding.fragment = this


        return binding.root
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        Log.d("popupTrade", "dismiss")
    }

    abstract fun observeValue(value: String)
    abstract fun executeTrade()

}

fun Float.round(decimals: Int): Float {
    var multiplier = 1f
    repeat(decimals) { multiplier *= 10f }
    return (this * multiplier).toInt() / multiplier
}

