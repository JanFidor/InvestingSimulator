package com.example.investingsimulator.Room_Fuckery.Favourite

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [StockFavourite::class], version = 0)
abstract class StockFavouriteDB : RoomDatabase() {
    abstract val stockDAO: StockFavouriteDAO

    companion object {

        @Volatile
        private var INSTANCE: StockFavouriteDB? = null

        fun getInstance(context: Context): StockFavouriteDB {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        StockFavouriteDB::class.java,
                        "walletDB"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}