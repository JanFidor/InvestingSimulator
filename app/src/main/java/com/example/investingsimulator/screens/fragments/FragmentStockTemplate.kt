package com.example.investingsimulator.screens.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.investingsimulator.R
import com.example.investingsimulator.databinding.FragmentStockListBinding
import com.example.investingsimulator.models.stockModel.StockTemplate
import com.example.investingsimulator.screens.recyclerViewAdapters.StockTemplateAdapter
import com.example.investingsimulator.screens.viewModels.ViewModelTemplate
import com.example.investingsimulator.room.templates.StockTemplateRoom
import com.jakewharton.rxbinding4.appcompat.queryTextChangeEvents
import io.reactivex.rxjava3.kotlin.subscribeBy
import java.util.*
import java.util.concurrent.TimeUnit

abstract class FragmentStockTemplate<T : StockTemplateRoom, U : StockTemplate> :  Fragment() {
    private lateinit var binding: FragmentStockListBinding
    protected open val viewModel: ViewModelTemplate<T, U> by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle? ): View {

        binding = FragmentStockListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.fragment = this

        val _funds = this.activity?.getPreferences(Context.MODE_PRIVATE)?.getFloat("FUNDS", 0.0F) ?: 0.0F
        binding.funds.text = getString(R.string.text_funds_amount, _funds)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(binding.root.context)
        val adapter = getAdapter()
        recyclerView.adapter = adapter

        viewModel.stockVisible.observe(viewLifecycleOwner) {recipes ->
            adapter.reload(recipes ?: listOf())
        }

        binding.searchView
            .queryTextChangeEvents()
            .debounce(500, TimeUnit.MILLISECONDS)
            .map{it.queryText.toString()}
            .map { it.lowercase(Locale.getDefault()).trim() }
            .distinctUntilChanged()
            .subscribeBy { viewModel.updateSearch(it)}

        viewModel.stockVisible.observe(viewLifecycleOwner, {adapter.reload(viewModel.stockVisible.value ?: listOf())})

        }

    abstract fun getAdapter(): StockTemplateAdapter<T, U>

}