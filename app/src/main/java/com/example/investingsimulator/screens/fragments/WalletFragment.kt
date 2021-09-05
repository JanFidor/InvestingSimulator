package com.example.investingsimulator.screens.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.investingsimulator.databinding.FragmentSettingsBinding
import com.example.investingsimulator.databinding.FragmentWalletBinding
import com.example.investingsimulator.models.stockModel.StockFavourite
import com.example.investingsimulator.room.favourite.StockFavouriteRoom

class WalletFragment :  Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSettingsBinding.inflate(inflater, container, false)
        binding.apply{
            lifecycleOwner = this@WalletFragment.viewLifecycleOwner
            stock = StockFavourite(StockFavouriteRoom("AAPL", "Apple"))
        }
        return binding.root
    }
}