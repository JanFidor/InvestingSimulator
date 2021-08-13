package com.example.investingsimulator.Screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.investingsimulator.databinding.FragmentSearchBinding

class SearchFragment :  Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        /*return super.onCreateView(inflater, container, savedInstanceState)*/
        val binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
        //val view: View = inflater.inflate(R.layout., container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}