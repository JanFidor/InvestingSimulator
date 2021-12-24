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
import com.example.investingsimulator.screens.GraphDataBindingAdapter.Companion.setDefaultCandleStyle
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

            val values = stockAnalysis.candleData

            val set = CandleDataSet(values, "")

            set.setDefaultBackgroundStyle()
            set.setDefaultCandleStyle()
            set.setDrawValues(false)


            var t: Toast? = null


            view.setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
                override fun onValueSelected(e: Entry?, h: Highlight?) {
                    (e as CandleEntry).let{
                        t?.cancel()
                        t = Toast.makeText(
                            view.context,
                            formatToastString(e, stockAnalysis),
                            Toast.LENGTH_LONG
                        )
                        t?.setGravity(Gravity.TOP, 0, 300)

                        t?.show()
                    }
                }

                override fun onNothingSelected() {}
            })

            view.legend.isEnabled = false
            view.description.isEnabled = false

            view.setDefaultAxisStyle()
            view.xAxis.valueFormatter = CustomGraphFormatter(stockAnalysis.dateData)
            val data1 = CandleData(set)
            view.data = data1
            view.invalidate()
        }

        private fun formatToastString(candleEntry: CandleEntry, stockAnalysis: StockAnalysis): String{
            return with(candleEntry){
                    "open: $open \n" +
                    "close: $close \n" +
                    "high: $high \n" +
                    "low: $low \n" +
                    "volume: ${stockAnalysis.volumeData[x.toInt()]} \n" +
                    "date: ${stockAnalysis.dateData[x.toInt()]}"
                }
        }

        private fun CandleStickChart.setDefaultAxisStyle(){
            this.isScaleXEnabled = false
            this.isScaleYEnabled = false
            this.axisRight.setDrawLabels(false)
            this.axisLeft.textColor = Color.GRAY
            this.xAxis.textColor = Color.GRAY
        }

        private fun CandleDataSet.setDefaultBackgroundStyle(){
            this.isHighlightEnabled = true
            this.setDrawIcons(false)
            this.axisDependency = YAxis.AxisDependency.LEFT
            this.color = Color.rgb(80, 80, 80)
            this.shadowColor = Color.DKGRAY
            this.shadowWidth = 2f
        }

        private fun CandleDataSet.setDefaultCandleStyle(){
            this.decreasingColor = Color.RED
            this.decreasingPaintStyle = Paint.Style.FILL
            this.increasingPaintStyle = Paint.Style.FILL
            this.increasingColor = Color.GREEN
            this.neutralColor = Color.BLUE
        }
    }
}




