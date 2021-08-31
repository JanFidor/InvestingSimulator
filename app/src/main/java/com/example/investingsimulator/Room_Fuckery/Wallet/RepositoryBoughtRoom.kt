package com.example.investingsimulator.Room_Fuckery.Wallet

import android.app.Application
import com.example.investingsimulator.Room_Fuckery.templates.RoomRepository

class RepositoryBoughtRoom(application: Application)
    : RoomRepository <StockBoughtRoom> (application){
    override val stockDao = db.stockBoughtDAO
}