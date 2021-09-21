package com.example.investingsimulator.screens.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.investingsimulator.databinding.FragmentSettingsBinding
import com.example.investingsimulator.databinding.FragmentSettingsBindingImpl
import com.example.investingsimulator.models.stockModel.StockFavourite
import com.example.investingsimulator.room.favourite.StockFavouriteRoom
import com.example.investingsimulator.screens.viewModels.ViewModelBought
import com.example.investingsimulator.screens.viewModels.ViewModelFavourite

class WalletFragment :  Fragment() {
    private val viewModelBought: ViewModelBought by activityViewModels()
    private val viewModelFavourite: ViewModelFavourite by activityViewModels()

    private val sharedPrefs = activity?.getPreferences(Context.MODE_PRIVATE)

    val _funds = sharedPrefs?.getFloat("FUNDS", 0.0F) ?: 0.0F
    val funds = _funds.toString()
    val walletValue = viewModelBought.getValue().toString()
    val stocksBought = viewModelBought.getSize().toString()
    val stocksFavourite = viewModelFavourite.getSize().toString()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSettingsBinding.inflate(inflater, container, false)


        binding.apply{
            lifecycleOwner = this@WalletFragment.viewLifecycleOwner
            fragment = this@WalletFragment
        }
        return binding.root
    }
}