package com.example.investingsimulator.models

import android.graphics.Color

object TextFormatting {
    @JvmStatic
    fun getPercentColor(change: Double): Int {
        return when {
            change < 0.0 -> Color.RED
            change == 0.0 -> Color.BLUE
            change > 0.0 -> Color.GREEN
            else -> 0
        }
    }

    @JvmStatic
    fun getPercentText(change: Double):String{

        val s = "${"%.2f".format(change)}%"
        return when {
            change == 0.0 -> ""
            change < 0.0 -> s
            change > 0.0 -> "+$s"
            else -> ""
        }
    }
}