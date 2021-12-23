package com.example.investingsimulator.room.bought

import android.app.Application
import com.example.investingsimulator.room.templates.RepositoryTemplateRoom


class RepositoryBoughtRoom(application: Application)
    : RepositoryTemplateRoom <StockBoughtRoom> (application){
    override val stockDao = db.stockBoughtDAO
}