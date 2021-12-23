package com.example.investingsimulator.models

import java.text.SimpleDateFormat
import java.util.*


fun getCalculatedDate(days: Int): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
    val calendar = Calendar.getInstance()

    calendar.add(Calendar.DAY_OF_YEAR, days)
    return dateFormat.format(Date(calendar.timeInMillis))
}