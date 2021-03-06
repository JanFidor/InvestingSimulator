package com.example.investingsimulator.models

import java.text.SimpleDateFormat
import java.util.*


fun getCalculatedDate(days: Int): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
    val cal = Calendar.getInstance()

    cal.add(Calendar.DAY_OF_YEAR, days)
    return dateFormat.format(Date(cal.timeInMillis))
}