package com.example.investingsimulator.Room_Fuckery.templates

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.investingsimulator.Room_Fuckery.Favourite.StockFavouriteRoom
import com.example.investingsimulator.Room_Fuckery.Favourite.StockFavouriteDAO
import com.example.investingsimulator.Room_Fuckery.Wallet.StockBoughtRoom
import com.example.investingsimulator.Room_Fuckery.Wallet.StockBoughtDAO

@Database(entities = [StockBoughtRoom::class, StockFavouriteRoom::class], version = 3, exportSchema = false)
abstract class StockDB : RoomDatabase() {
    abstract val stockBoughtDAO: StockBoughtDAO
    abstract val stockFavouriteDAO: StockFavouriteDAO

    companion object {

        @Volatile
        private var INSTANCE: StockDB? = null

        fun getInstance(context: Context): StockDB {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        StockDB::class.java,
                        "stock_db"
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