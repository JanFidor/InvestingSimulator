package com.example.investingsimulator.screens.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.investingsimulator.room.favourite.StockFavouriteRoom
import com.example.investingsimulator.screens.viewModels.ViewModelFavourite

class FragmentStockFavourite : FragmentStockTemplate<StockFavouriteRoom>() {
    override val viewModel: ViewModelFavourite by activityViewModels()

}