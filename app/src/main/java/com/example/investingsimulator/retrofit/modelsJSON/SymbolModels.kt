package com.example.investingsimulator.retrofit


import com.example.investingsimulator.room.templates.StockTemplateRoom
import com.google.gson.annotations.SerializedName

interface SymbolInterface


data class SymbolWrapper2(
    @field:SerializedName("securities") var securities: SymbolWrapper1,
) : SymbolInterface

data class SymbolWrapper1(
    @field:SerializedName("security") var security: SymbolData,
)

data class SymbolsWrapper2(
    @field:SerializedName("securities") var securities: SymbolsWrapper1,
) : SymbolInterface

data class SymbolsWrapper1(
    @field:SerializedName("security") var security: Array<SymbolData>,
)

data class SymbolData(
    @field:SerializedName("symbol") override var symbol: String,
    @field:SerializedName("description") override var description: String?
) : StockTemplateRoom()

