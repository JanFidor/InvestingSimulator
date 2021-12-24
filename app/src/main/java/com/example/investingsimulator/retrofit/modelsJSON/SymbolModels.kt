package com.example.investingsimulator.retrofit.modelsJSON

import com.example.investingsimulator.room.templates.StockTemplateRoom


interface SymbolInterface


data class SymbolWrapper2(
    val securities: SymbolWrapper1,
) : SymbolInterface

data class SymbolWrapper1(
    val security: SymbolData,
)

data class SymbolsWrapper2(
    val securities: SymbolsWrapper1,
) : SymbolInterface

data class SymbolsWrapper1(
    val security: Array<SymbolData>,
)

data class SymbolData(
    override val symbol: String,
    override val description: String?
) : StockTemplateRoom()

