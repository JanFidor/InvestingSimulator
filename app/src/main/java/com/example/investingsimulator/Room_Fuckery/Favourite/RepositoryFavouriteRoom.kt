package com.example.investingsimulator.Room_Fuckery.Favourite

import android.app.Application
import com.example.investingsimulator.Room_Fuckery.templates.RoomRepository

class RepositoryFavouriteRoom(application: Application)
    : RoomRepository <StockFavouriteRoom> (application){
    override val stockDao = db.stockFavouriteDAO
}