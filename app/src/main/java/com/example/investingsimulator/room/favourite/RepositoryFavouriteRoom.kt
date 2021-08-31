package com.example.investingsimulator.room.favourite

import android.app.Application
import com.example.investingsimulator.room.templates.RoomRepository

class RepositoryFavouriteRoom(application: Application)
    : RoomRepository <StockFavouriteRoom> (application){
    override val stockDao = db.stockFavouriteDAO
}