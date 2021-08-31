package com.example.investingsimulator.room.bought

import android.app.Application
import com.example.investingsimulator.room.templates.RoomRepository

class RepositoryBoughtRoom(application: Application)
    : RoomRepository <StockBoughtRoom> (application){
    override val stockDao = db.stockBoughtDAO
}