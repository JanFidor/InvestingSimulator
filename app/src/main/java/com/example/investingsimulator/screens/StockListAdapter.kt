package com.example.investingsimulator.screens

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.investingsimulator.databinding.RowStockBinding
import com.example.investingsimulator.models.TextFormatting
import com.example.investingsimulator.models.stockModel.StockTemplate

class StockListAdapter(
    private var stockList: List<StockTemplate>,
    private val fragment: Fragment
    ) : RecyclerView.Adapter<StockListAdapter.StockHolder>() {

    fun reload(newStockList: List<StockTemplate>){
        stockList = newStockList
        notifyDataSetChanged()
        notifyItemRangeChanged(0, stockList.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RowStockBinding.inflate(inflater, parent, false)
        return StockHolder(binding, fragment)
    }

    override fun onBindViewHolder(holder: StockHolder, position: Int) = holder.bind(stockList[position])

    override fun getItemCount(): Int = stockList.size

    inner class StockHolder(
        private val binding: RowStockBinding,
        private val fragment: Fragment
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(stock: StockTemplate) {
            with(binding){
                lifecycleOwner = fragment.viewLifecycleOwner
                stockData = stock
                stockHolder = this@StockHolder
            }
        }

        fun openGraph(){
            return
        }
    }
}