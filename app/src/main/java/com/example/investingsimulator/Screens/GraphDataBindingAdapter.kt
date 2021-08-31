package com.example.investingsimulator.Screens

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

            val values = stockAnalysis.candleData

            /*for(v in values) Log.d("candle", "${v.open}   ${v.close}")*/

            /*val set = CandleDataSet(values, stockAnalysis.stockName)*/
            val set = CandleDataSet(values, "")

            set.isHighlightEnabled = true
            set.setDrawIcons(false)
            set.axisDependency = YAxis.AxisDependency.LEFT
            set.color = Color.rgb(80, 80, 80)
            set.shadowColor = Color.DKGRAY
            set.shadowWidth = 2f
            set.decreasingColor = Color.RED
            set.decreasingPaintStyle = Paint.Style.FILL
            set.increasingPaintStyle = Paint.Style.FILL
            /*set.increasingColor = Color.rgb(122, 242, 84)*/
            set.increasingColor = Color.GREEN
            set.neutralColor = Color.BLUE
            set.setDrawValues(false)

            /*view.description.textColor = Color.GRAY
            view.description.textSize = 12f
            view.description.text = stockAnalysis.stockName*/

            var t: Toast? = null

            view.setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
                override fun onValueSelected(e: Entry?, h: Highlight?) {
                    with(e as CandleEntry){
                        t?.cancel()
                        t = Toast.makeText(
                            view.context,
                            "open: $open \n" +
                                "close: $close \n" +
                                "high: $high \n" +
                                "low: $low \n" +
                                "volume: ${stockAnalysis.volumeData[x.toInt()]} \n" +
                                "date: ${stockAnalysis.dateData[x.toInt()]}",
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

            view.isScaleXEnabled = false
            view.isScaleYEnabled = false
            view.axisRight.setDrawLabels(false)
            view.axisLeft.textColor = Color.GRAY
            view.xAxis.textColor = Color.GRAY
            view.xAxis.valueFormatter = CustomGraphFormatter(stockAnalysis.dateData)
            val data1 = CandleData(set)
            view.data = data1
            view.invalidate()
        }
    }
}




