package com.example.investingsimulator.screens.recyclerViewAdapters

import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.investingsimulator.databinding.RowStockBinding
import com.example.investingsimulator.models.stockModel.StockTemplate
import com.example.investingsimulator.room.templates.StockTemplateRoom
import com.example.investingsimulator.screens.viewModels.ViewModelTemplate

abstract class StockTemplateAdapter<T : StockTemplateRoom, U : StockTemplate>(
    protected var stockList: List<StockTemplate>,
    protected val fragment: Fragment,
    protected val viewModel: ViewModelTemplate<T, U>
    ) : RecyclerView.Adapter<StockTemplateAdapter<T, U>.StockHolder<T>>() {

    protected abstract val visible:  Boolean

    fun reload(newStockList: List<StockTemplate>) {
        stockList = newStockList
        notifyDataSetChanged()
        notifyItemRangeChanged(0, stockList.size)
    }


    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockHolder<T>

    override fun onBindViewHolder(holder: StockHolder<T>, position: Int) =
        holder.bind(stockList[position])

    override fun getItemCount(): Int = stockList.size

    abstract inner class StockHolder<T  : StockTemplateRoom>(
        protected val binding: RowStockBinding,
        private val fragment: Fragment,
        private val viewModel: ViewModelTemplate<T, U>
    ) : RecyclerView.ViewHolder(binding.root) {

        open fun bind(stock: StockTemplate) {
            binding.let{
                it.lifecycleOwner = fragment.viewLifecycleOwner
                it.stockData = stock
                if (!visible) it.star.visibility = ImageView.INVISIBLE
            }
        }

        abstract fun openGraph()

        open fun observe(stock: StockTemplate){}
    }
}
