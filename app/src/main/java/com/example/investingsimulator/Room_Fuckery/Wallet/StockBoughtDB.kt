package com.example.investingsimulator.Room_Fuckery.Wallet

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [StockBought::class], version = 0)
abstract class StockBoughtDB : RoomDatabase() {
    abstract val stockStockBoughtDAO: StockBoughtDAO

    companion object {

        @Volatile
        private var INSTANCE: StockBoughtDB? = null

        fun getInstance(context: Context): StockBoughtDB {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        StockBoughtDB::class.java,
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