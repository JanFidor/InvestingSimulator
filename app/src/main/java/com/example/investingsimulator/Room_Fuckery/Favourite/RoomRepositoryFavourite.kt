package com.example.investingsimulator.Room_Fuckery.Favourite

import android.app.Application
import com.example.investingsimulator.Room_Fuckery.templates.RoomRepository

class RoomRepositoryFavourite(application: Application)
    : RoomRepository <StockFavourite> (application){
    override val stockDao = db.stockFavouriteDAO
}