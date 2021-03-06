package com.example.investingsimulator.screens.recyclerViewAdapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.investingsimulator.databinding.RowStockBinding
import com.example.investingsimulator.models.TextFormatting
import com.example.investingsimulator.models.stockModel.StockFavourite
import com.example.investingsimulator.models.stockModel.StockTemplate
import com.example.investingsimulator.room.favourite.StockFavouriteRoom
import com.example.investingsimulator.screens.ToastType
import com.example.investingsimulator.screens.fragments.FragmentStockFavourite
import com.example.investingsimulator.screens.fragments.FragmentStockFavouriteDirections
import com.example.investingsimulator.screens.viewModels.ViewModelFavourite

class StockFavouriteAdapter(
    stockList: List<StockFavourite>,
    fragment: FragmentStockFavourite,
    viewModel: ViewModelFavourite
) : StockTemplateAdapter<StockFavouriteRoom, StockFavourite>(stockList, fragment, viewModel) {

    override val visible: Boolean
        get() = true

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StockFavouriteHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RowStockBinding.inflate(inflater, parent, false)

        return StockFavouriteHolder(binding, fragment as FragmentStockFavourite,
            viewModel as ViewModelFavourite
        )
    }

    override fun onBindViewHolder(holder: StockHolder<StockFavouriteRoom>, position: Int){
        with(holder as StockFavouriteHolder){
            this.bind(stockList[position] as StockFavourite)
        }
    }


    inner class StockFavouriteHolder
        (binding: RowStockBinding, fragment: FragmentStockFavourite, viewModel: ViewModelFavourite)
        : StockHolder<StockFavouriteRoom>(binding, fragment, viewModel){

        fun bind(stock: StockFavourite){
            super.bind(stock)
            binding.stockHolder = this
            TextFormatting.setObservedColor(stock.observed.value ?: false, binding.star)
        }

        override fun openGraph() {
            val action = FragmentStockFavouriteDirections
                .actionSearchFragmentToFragmentStockDetails(
                    binding.stockData, binding.stockData?.symbol ?: "")
            fragment.findNavController().navigate(action)
        }


        override fun observe(stock: StockTemplate){
            val stockFavourite = stock as StockFavourite
            TextFormatting.setObservedColor(stockFavourite.observed.value?.not() ?: true, binding.star)

            if (stockFavourite.observed.value != false) {
                ToastType.ObservedRemove.getToast(binding.root.context, stockFavourite.symbol).show()
                viewModel.deleteStock(stockFavourite.getCore())
            }
            else {
                ToastType.ObservedAdd.getToast(binding.root.context, stockFavourite.symbol).show()
                viewModel.addStock(stockFavourite.getCore())
            }
            stockFavourite.changeObservedStatus()
        }
    }
}