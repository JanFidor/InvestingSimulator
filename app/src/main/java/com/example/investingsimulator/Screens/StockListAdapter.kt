package com.example.investingsimulator.Screens

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.investingsimulator.databinding.RowStockBinding
import com.example.investingsimulator.models.TextFormatting
import com.example.investingsimulator.models.stockModel.StockTemplate

class StockListAdapter(
    private var stockList: List<StockTemplate>,
    private val activity: FragmentActivity
    ) : RecyclerView.Adapter<StockListAdapter.StockHolder>() {

    inner class StockHolder(
        private val binding: RowStockBinding,
        private val a: FragmentActivity
        ) : RecyclerView.ViewHolder(binding.root) {


        fun bind(stock: StockTemplate) {
            with(binding){
                stockData = stock
                stockHolder = this@StockHolder
            }
        }

        fun openGraph(){
            return
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RowStockBinding.inflate(inflater, parent, false)
        return StockHolder(binding, activity)
    }

    override fun onBindViewHolder(holder: StockHolder, position: Int) = holder.bind(stockList[position])


    override fun getItemCount(): Int = stockList.size
}