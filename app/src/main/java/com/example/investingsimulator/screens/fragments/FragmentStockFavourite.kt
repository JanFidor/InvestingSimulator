package com.example.investingsimulator.screens.fragments

import androidx.fragment.app.activityViewModels
import com.example.investingsimulator.models.stockModel.StockFavourite
import com.example.investingsimulator.models.stockModel.StockTemplate
import com.example.investingsimulator.room.bought.StockBoughtRoom
import com.example.investingsimulator.room.favourite.StockFavouriteRoom
import com.example.investingsimulator.screens.StockTemplateAdapter
import com.example.investingsimulator.screens.recyclerViewAdapters.StockFavouriteAdapter
import com.example.investingsimulator.screens.viewModels.ViewModelFavourite
import com.example.investingsimulator.screens.viewModels.ViewModelTemplate

class FragmentStockFavourite : FragmentStockTemplate<StockFavouriteRoom, StockFavourite>() {
    override val viewModel: ViewModelFavourite by activityViewModels()

    override fun getAdapter(): StockFavouriteAdapter {
        val list = viewModel.stockVisible.value ?: listOf()

        return StockFavouriteAdapter(list, this, viewModel)
    }

}