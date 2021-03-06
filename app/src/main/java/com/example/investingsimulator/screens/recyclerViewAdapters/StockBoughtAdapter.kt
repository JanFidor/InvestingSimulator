package com.example.investingsimulator.screens.recyclerViewAdapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.investingsimulator.databinding.RowStockBinding
import com.example.investingsimulator.models.stockModel.StockBought
import com.example.investingsimulator.room.bought.StockBoughtRoom
import com.example.investingsimulator.screens.fragments.FragmentStockBought
import com.example.investingsimulator.screens.fragments.FragmentStockBoughtDirections
import com.example.investingsimulator.screens.viewModels.ViewModelBought


class StockBoughtAdapter (
    stockList: List<StockBought>,
    fragment: FragmentStockBought,
    viewModel: ViewModelBought
) : StockTemplateAdapter<StockBoughtRoom, StockBought>(stockList, fragment, viewModel) {

    override val visible: Boolean
        get() = false

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StockBoughtHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RowStockBinding.inflate(inflater, parent, false)

        return StockBoughtHolder(binding, fragment as FragmentStockBought,
            viewModel as ViewModelBought
        )
    }

    override fun onBindViewHolder(holder: StockHolder<StockBoughtRoom>, position: Int){
        with(holder as StockBoughtHolder){
            this.bind(stockList[position] as StockBought)
        }
    }


    inner class StockBoughtHolder
        (binding: RowStockBinding, fragment: FragmentStockBought, viewModel: ViewModelBought)
        : StockHolder<StockBoughtRoom>(binding, fragment, viewModel){

        fun bind(stock: StockBought){
            super.bind(stock)
            binding.stockHolder = this
        }

        override fun openGraph() {
            val action = FragmentStockBoughtDirections
                .actionWalletFragmentToFragmentStockDetails(
                    binding.stockData, binding.stockData?.symbol ?: "")
            fragment.findNavController().navigate(action)
        }

    }


}