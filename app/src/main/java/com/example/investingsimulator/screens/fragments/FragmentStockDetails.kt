package com.example.investingsimulator.screens.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.navArgs
import com.example.investingsimulator.databinding.FragmentDetailsBinding
import com.example.investingsimulator.screens.popups.DialogFragmentBuy
import com.example.investingsimulator.screens.popups.DialogFragmentSell
import com.example.investingsimulator.screens.viewModels.ViewModelBought

class FragmentStockDetails :  Fragment() {
    companion object{
        val STOCK = "stockData"
    }

    private val args: FragmentStockDetailsArgs by navArgs()
    protected val viewModelBought: ViewModelBought by activityViewModels()

    val stock by lazy{args.stockData}

    private val canBuy: Boolean
        get() = stock?.last?.value ?: 0f != 0f
    private val canSell: Boolean
        get() = viewModelBought.getAmount(args.stockData?.symbol.toString()) > 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentDetailsBinding.inflate(inflater, container, false)
        binding.apply{
            lifecycleOwner = this@FragmentStockDetails.viewLifecycleOwner
            stock = this@FragmentStockDetails.stock
            fragment = this@FragmentStockDetails
        }
        return binding.root
    }

    fun buy(){
        Log.d("popup", "buy")
        if(canBuy){
            args.stockData?.let {
                val fragment = DialogFragmentBuy(it, viewModelBought, this)
                fragment.show(parentFragmentManager, "BUY")
            }
        }
        else{}
    }

    fun sell(){
        Log.d("popup", "sell")
        if(canSell){
            args.stockData?.let {
                val fragment = DialogFragmentSell(it, viewModelBought, this)
                fragment.show(parentFragmentManager, "SELL")
            }
        }
        else{}
    }
}