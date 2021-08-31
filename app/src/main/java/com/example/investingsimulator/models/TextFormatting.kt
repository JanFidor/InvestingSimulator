package com.example.investingsimulator.models

import android.graphics.Color

object TextFormatting {

    fun getPercentColor(change: Double): Int {

        return when {
            change < 0.0 -> Color.RED
            change == 0.0 -> Color.BLUE
            change > 0.0 -> Color.GREEN
            else -> 0
        }
    }
    fun getPercentText(change: Double):String{
        val percent = ((change - 1) * 100)

        val s = "${"%.2f".format(percent)}%"
        return when {
            change < 0.0 -> "-$s"
            change == 0.0 -> s
            change > 0.0 -> "+$s"
            else -> ""
        }
    }
}