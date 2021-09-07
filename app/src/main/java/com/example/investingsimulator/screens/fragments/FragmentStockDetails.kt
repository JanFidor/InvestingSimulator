package com.example.investingsimulator.screens.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.investingsimulator.databinding.FragmentSettingsBinding
import com.example.investingsimulator.models.stockModel.StockFavourite
import com.example.investingsimulator.models.stockModel.StockTemplate
import com.example.investingsimulator.room.favourite.StockFavouriteRoom

class FragmentStockDetails :  Fragment() {

    companion object{
        val STOCK = "stockData"
    }

    private val args: FragmentStockDetailsArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSettingsBinding.inflate(inflater, container, false)
        binding.apply{
            lifecycleOwner = this@FragmentStockDetails.viewLifecycleOwner
            stock = args.stockData as StockFavourite?
        }
        return binding.root
    }
}