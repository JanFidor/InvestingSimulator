package com.example.investingsimulator.screens

import android.graphics.Color
import android.graphics.Paint
import android.view.Gravity
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods
import com.example.investingsimulator.models.CustomGraphFormatter
import com.example.investingsimulator.models.StockAnalysis
import com.github.mikephil.charting.charts.CandleStickChart
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.CandleData
import com.github.mikephil.charting.data.CandleDataSet
import com.github.mikephil.charting.data.CandleEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener


@BindingMethods(value = [
    BindingMethod(
        type = CandleStickChart::class,
        attribute = "android:borders_visible",
        method = "setDrawBorders"),

    BindingMethod(
        type = CandleStickChart::class,
        attribute = "android:borders_color",
        method = "setBorderColor"),
])

class GraphDataBindingAdapter{
    companion object{

        @BindingAdapter("android:dataSet")
        @JvmStatic
        fun borderVisibility(view: CandleStickChart, stockAnalysis: StockAnalysis?) {
            if(stockAnalysis == null) return

            val candles = stockAnalysis.candles
            val candlesDataSet = CandleDataSet(candles, "")

            candlesDataSet.isHighlightEnabled = true
            candlesDataSet.setDrawIcons(false)
            candlesDataSet.axisDependency = YAxis.AxisDependency.LEFT
            candlesDataSet.color = Color.rgb(80, 80, 80)
            candlesDataSet.shadowColor = Color.DKGRAY
            candlesDataSet.shadowWidth = 2f
            candlesDataSet.decreasingColor = Color.RED
            candlesDataSet.decreasingPaintStyle = Paint.Style.FILL
            candlesDataSet.increasingPaintStyle = Paint.Style.FILL
            candlesDataSet.increasingColor = Color.GREEN
            candlesDataSet.neutralColor = Color.BLUE
            candlesDataSet.setDrawValues(false)

            var toast: Toast? = null

            view.setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
                override fun onValueSelected(e: Entry?, h: Highlight?) {
                    with(e as CandleEntry){
                        toast?.cancel()
                        toast = Toast.makeText(
                            view.context,
                            "open: $open \n" +
                                "close: $close \n" +
                                "high: $high \n" +
                                "low: $low \n" +
                                "volume: ${stockAnalysis.volumes[x.toInt()]} \n" +
                                "date: ${stockAnalysis.dates[x.toInt()]}",
                            Toast.LENGTH_LONG
                        )
                        toast?.setGravity(Gravity.TOP, 0, 300)

                        toast?.show()
                    }
                }

                override fun onNothingSelected() {}
            })

            view.legend.isEnabled = false
            view.description.isEnabled = false

            view.isScaleXEnabled = false
            view.isScaleYEnabled = false
            view.axisRight.setDrawLabels(false)
            view.axisLeft.textColor = Color.GRAY
            view.xAxis.textColor = Color.GRAY
            view.xAxis.valueFormatter = CustomGraphFormatter(stockAnalysis.dates)
            val candlesFinal = CandleData(candlesDataSet)
            view.data = candlesFinal
            view.invalidate()
        }
    }
}




