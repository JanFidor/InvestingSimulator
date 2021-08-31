package com.example.investingsimulator.screens

import android.graphics.Color
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

    fun reload(newStockList: List<StockTemplate>){
        stockList = newStockList
        notifyDataSetChanged()
        notifyItemRangeChanged(0, stockList.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RowStockBinding.inflate(inflater, parent, false)
        return StockHolder(binding, activity)
    }

    override fun onBindViewHolder(holder: StockHolder, position: Int) = holder.bind(stockList[position])

    override fun getItemCount(): Int = stockList.size

    inner class StockHolder(
        private val binding: RowStockBinding,
        private val a: FragmentActivity
    ) : RecyclerView.ViewHolder(binding.root) {

        fun getPercentColor(change: Double): Int {
            return when {
                change < 0.0 -> Color.RED
                change == 0.0 -> Color.BLUE
                change > 0.0 -> Color.GREEN
                else -> 0
            }
        }

        fun bind(stock: StockTemplate) {
            with(binding){
                stockData = stock
                stockHolder = this@StockHolder
                /*decorator = TextFormatting*/
            }
        }

        fun openGraph(){
            return
        }
    }
}