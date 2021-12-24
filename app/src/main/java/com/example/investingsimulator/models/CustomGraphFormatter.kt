package com.example.investingsimulator.models

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.ValueFormatter


class CustomGraphFormatter(private val dates: List<String>) : ValueFormatter() {
    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        return dates[value.toInt()]
    }
}