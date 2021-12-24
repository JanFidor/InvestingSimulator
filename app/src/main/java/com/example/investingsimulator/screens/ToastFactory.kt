package com.example.investingsimulator.screens

import android.content.Context
import android.widget.Toast

enum class ToastType{
    ObservedAdd{
        override fun toastString(stockSymbol: String): String {
            return "added $stockSymbol to observed"
        }
    },
    ObservedRemove{
        override fun toastString(stockSymbol: String): String {
            return "deleted $stockSymbol from observed"
        }
    };

    protected abstract fun toastString(stockSymbol: String): String
    fun getToast(context: Context, stockSymbol: String): Toast{
        return Toast.makeText(
            context,
            toastString(toastString(stockSymbol)),
            Toast.LENGTH_SHORT
        )
    }
}