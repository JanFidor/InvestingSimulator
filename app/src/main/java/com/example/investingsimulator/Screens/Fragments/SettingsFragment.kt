package com.example.investingsimulator.Screens.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.investingsimulator.API_Fuckery.MarketHistoryMultiple
import com.example.investingsimulator.API_Fuckery.RetrofitInstance
import com.example.investingsimulator.databinding.FragmentSettingsBinding
import com.example.investingsimulator.models.StockAnalysis
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SettingsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSettingsBinding.inflate(inflater, container, false)

        binding.apply {
            val stocks = "AAPL"

            val start = "2021-07-25"
            val end = "2021-08-25"

            var data: StockAnalysis?

            val call3 = RetrofitInstance.InterfaceAPI.getLongHistory(stocks, start, end)
            call3.enqueue(object : Callback<MarketHistoryMultiple> {
                override fun onFailure(call: Call<MarketHistoryMultiple>, t: Throwable?) {
                    Log.e("api", t?.message ?: "No message")
                }

                override fun onResponse(call: Call<MarketHistoryMultiple>, response: Response<MarketHistoryMultiple>?) {
                    response?.let {
                        if (response.isSuccessful) {
                            Log.d("api", response.body().toString())
                            data = StockAnalysis(response.body()?.history?.day ?: arrayOf())
                            Log.d("api", data?.candleData.toString())
                            dataSet = data

                        }
                    }
                }
            })


}
return binding.root
}

override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
super.onViewCreated(view, savedInstanceState)
}

override fun onDestroy() {
super.onDestroy()
}
}