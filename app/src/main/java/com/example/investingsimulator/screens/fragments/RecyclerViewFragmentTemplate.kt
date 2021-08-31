package com.example.investingsimulator.screens.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.investingsimulator.screens.StockListAdapter
import com.example.investingsimulator.screens.viewModels.ViewModelTemplate
import com.example.investingsimulator.databinding.FragmentSearchBinding

class RecyclerViewFragmentTemplate :  Fragment() {
    private lateinit var binding: FragmentSearchBinding

    private val viewModel: ViewModelTemplate by activityViewModels()
    private val fragment = this

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle? ): View {

        binding = FragmentSearchBinding.inflate(inflater, container, false)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            /*viewModel = viewModel
            recipeFragment = fragment*/
        }


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

    }
}