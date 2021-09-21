package com.example.investingsimulator.screens.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.investingsimulator.databinding.FragmentDetailsBinding
import com.example.investingsimulator.databinding.FragmentSettingsBinding
import com.example.investingsimulator.models.stockModel.StockBought
import com.example.investingsimulator.models.stockModel.StockFavourite
import com.example.investingsimulator.models.stockModel.StockTemplate
import com.example.investingsimulator.room.favourite.StockFavouriteRoom
import com.example.investingsimulator.screens.popups.DialogFragmentBuy
import com.example.investingsimulator.screens.popups.DialogFragmentSell
import com.example.investingsimulator.screens.viewModels.ViewModelBought

class FragmentStockDetails :  Fragment() {

    companion object{
        val STOCK = "stockData"
    }

    private val args: FragmentStockDetailsArgs by navArgs()
    protected val viewModelBought: ViewModelBought by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentDetailsBinding.inflate(inflater, container, false)
        binding.apply{
            lifecycleOwner = this@FragmentStockDetails.viewLifecycleOwner
            stock = args.stockData
            fragment = this@FragmentStockDetails
        }
        return binding.root
    }

    fun buy(){
        Log.d("popup", "buy")
        args.stockData?.let {
            val fragment = DialogFragmentBuy(it, viewModelBought, this)
            fragment.show(parentFragmentManager, "BUY")
        }

    }

    fun sell(){
        Log.d("popup", "sell")
        args.stockData?.let {
            val fragment = DialogFragmentSell(it, viewModelBought, this)
            fragment.show(parentFragmentManager, "SELL")
        }
    }
}