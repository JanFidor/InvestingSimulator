package com.example.investingsimulator.screens.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.investingsimulator.databinding.FragmentStockListBinding
import com.example.investingsimulator.screens.StockListAdapter
import com.example.investingsimulator.screens.viewModels.ViewModelTemplate
import com.example.investingsimulator.room.templates.StockTemplateRoom

abstract class FragmentStockTemplate<T : StockTemplateRoom> :  Fragment() {
    private lateinit var binding: FragmentStockListBinding
    protected open val viewModel: ViewModelTemplate<T> by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle? ): View {

        binding = FragmentStockListBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.fragment = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(binding.root.context)
        val adapter = StockListAdapter(viewModel.stockVisible.value ?: listOf(), requireActivity())
        recyclerView.adapter = adapter

        viewModel.stockVisible.observe(viewLifecycleOwner) {recipes ->
            adapter.reload(recipes ?: listOf())
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.searched.value = newText
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.searched.value = query
                return false
            }

        })

        viewModel.searched.observe(viewLifecycleOwner, {viewModel.updateSearch()})
        viewModel.stockVisible.observe(viewLifecycleOwner, {adapter.reload(viewModel.stockVisible.value ?: listOf())})

        }
}