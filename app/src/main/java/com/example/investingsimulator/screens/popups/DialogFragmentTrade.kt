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
import com.example.investingsimulator.databinding.PopupTradeBinding
import com.example.investingsimulator.models.stockModel.StockTemplate
import com.example.investingsimulator.screens.fragments.FragmentStockDetails
import com.example.investingsimulator.screens.viewModels.ViewModelBought
import com.example.investingsimulator.screens.viewModels.ViewModelFavourite
import java.util.*

abstract class DialogFragmentTrade(val stock: StockTemplate, protected val viewModelBought: ViewModelBought, val frag: FragmentStockDetails)  : DialogFragment() {
    abstract val symbol: String

    private lateinit var binding: PopupTradeBinding
/*    protected val viewModelBought: ViewModelBought by activityViewModels()*/

    abstract val tradeType: String

    val transactionValue  =  MutableLiveData("0.0")
    val transactionAmount = MutableLiveData("0.0")

    protected val sharedPrefs = frag.activity?.getPreferences(Context.MODE_PRIVATE)

    val _funds = sharedPrefs?.getFloat("FUNDS", 0.0F) ?: 0.0F
    val funds = _funds.toString()

    val last = stock.last.value.toString()
    val _last = stock.last.value!!.toFloat()
    val _change = stock.change.value!!.toFloat()

    abstract val _owned: Float
    abstract val owned: String
    /*abstract val owned: MutableLiveData<String>*/



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        Log.d("popupTrade", "create  $last")

        binding = PopupTradeBinding.inflate(inflater, container, false)

        // Beautiful magic scroll that removes addition background for window UI
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))


        binding.apply {
            lifecycleOwner = viewLifecycleOwner
        }
        binding.fragment = this

        /*binding.moneyBuyingV.onTextChange(::observeValue)
        binding.stockBuyingV.onTextChange(::observeAmount)*/

        /*binding.moneyBuyingV.setOnEditorActionListener { v, actionId, event ->
            var handled = false
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                Log.d("trade", "buy option")
                observeValue(binding.moneyBuyingV.text.toString())
                handled = true
            }
            handled
        }

        binding.stockBuyingV.setOnEditorActionListener { v, actionId, event ->
            var handled = false
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                Log.d("trade", "sell option")
                observeAmount(binding.stockBuyingV.text.toString())
                handled = true;
            }
            handled
        }*/

        /*transactionValue.observe(viewLifecycleOwner, ::observeValue)
        transactionAmount.observe(viewLifecycleOwner, ::observeAmount)*/


        return binding.root
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        Log.d("popupTrade", "dismiss")
    }

    abstract fun observeAmount(amount: String)
    abstract fun observeValue(value: String)

    abstract fun executeTrade()

    /*
    protected fun EditText.onTextChange(observingFunction: (String) -> Unit){
        this.setOnEditorActionListener { v, actionId, event ->
            var handled = false
            if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_PREVIOUS) {
                Log.d("trade", "buy option")
                observingFunction(this.text.toString())
                handled = true
            }
            handled
        }
        this.onFocusChangeListener.onFocusChange(view, hasFocus())
        this.setOnFocusChangeListener { _, hasFocus ->
            Log.d("change", "change")
            if(!hasFocus){observingFunction(this.text.toString()) }
        }

        this.addTextChangedListener(object: TextWatcher {
            var delay : Long = 1000 // 1
            var timer = Timer()
            override fun afterTextChanged(p0: Editable?) {
                timer = Timer()
                timer.schedule(object : TimerTask() {
                    override fun run() {
                        observingFunction(p0.toString())
                    }
                }, delay)
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                timer.cancel()
                timer.purge()
            }
        })
    }*/

}

fun Float.round(decimals: Int): Float {
    var multiplier = 1f
    repeat(decimals) { multiplier *= 10f }
    return (this * multiplier).toInt() / multiplier
}

