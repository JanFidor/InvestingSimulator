package com.example.investingsimulator.Room_Fuckery.Wallet

import android.app.Application
import com.example.investingsimulator.Room_Fuckery.templates.RoomRepository

class RoomRepositoryBought(application: Application)
    : RoomRepository <StockBought> (application){
    override val stockDao = db.stockBoughtDAO
}