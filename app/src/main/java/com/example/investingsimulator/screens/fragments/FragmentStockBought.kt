package com.example.investingsimulator.screens.fragments

import androidx.fragment.app.activityViewModels
import com.example.investingsimulator.room.bought.StockBoughtRoom
import com.example.investingsimulator.screens.viewModels.ViewModelBought

class FragmentStockBought : FragmentStockTemplate<StockBoughtRoom>() {
    override val viewModel: ViewModelBought by activityViewModels()
}