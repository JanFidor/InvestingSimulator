package com.example.investingsimulator.Room_Fuckery.templates

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "template_stock_database")
abstract class Stock(
    @PrimaryKey(autoGenerate = false)
    open val symbol: String
)