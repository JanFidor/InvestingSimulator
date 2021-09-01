package com.example.investingsimulator.room.favourite

import android.app.Application
import com.example.investingsimulator.room.templates.RepositoryTemplateRoom

class RepositoryFavouriteRoom(application: Application)
    : RepositoryTemplateRoom <StockFavouriteRoom> (application){
    override val stockDao = db.stockFavouriteDAO
}