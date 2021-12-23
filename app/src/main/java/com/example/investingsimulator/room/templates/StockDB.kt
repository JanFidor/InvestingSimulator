package com.example.investingsimulator.room.templates

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.investingsimulator.room.favourite.StockFavouriteRoom
import com.example.investingsimulator.room.favourite.StockFavouriteDAO
import com.example.investingsimulator.room.bought.StockBoughtRoom
import com.example.investingsimulator.room.bought.StockBoughtDAO

@Database(entities = [StockBoughtRoom::class, StockFavouriteRoom::class], version = 3, exportSchema = false)
abstract class StockDB : RoomDatabase() {
    abstract val stockBoughtDAO: StockBoughtDAO
    abstract val stockFavouriteDAO: StockFavouriteDAO

    companion object {

        @Volatile
        private var INSTANCE: StockDB? = null

        fun getInstance(context: Context): StockDB {
            synchronized(this) {
                assignSingletonValue(context)

                // assignSingletonValue() makes sure it's not null
                return INSTANCE!!
            }
        }

        private fun initializeStockDB(context: Context): StockDB {
            val stockDB = Room.databaseBuilder(context, StockDB::class.java, "stock_db")
            return stockDB
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        }

        private fun assignSingletonValue(context: Context){
            if (INSTANCE == null){
                INSTANCE = initializeStockDB(context.applicationContext)
            }
        }


    }

}