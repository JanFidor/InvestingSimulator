package com.example.investingsimulator.screens.fragments

import androidx.fragment.app.activityViewModels
import com.example.investingsimulator.models.stockModel.StockBought
import com.example.investingsimulator.room.bought.StockBoughtRoom
import com.example.investingsimulator.screens.recyclerViewAdapters.StockBoughtAdapter
import com.example.investingsimulator.screens.viewModels.ViewModelBought

class FragmentStockBought : FragmentStockTemplate<StockBoughtRoom, StockBought>() {

    override val viewModel: ViewModelBought by activityViewModels()

    override fun getAdapter(): StockBoughtAdapter {
        val list = viewModel.stockVisible.value ?: listOf()

        return StockBoughtAdapter(list, this, viewModel)
    }

}