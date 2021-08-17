package com.example.investingsimulator

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.investingsimulator.Room_Fuckery.Wallet.StockBought
import com.example.investingsimulator.Room_Fuckery.Wallet.StockBoughtDAO
import com.example.investingsimulator.Room_Fuckery.templates.StockDB
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class SimpleEntityReadWriteTest {
    private lateinit var userDao: StockBoughtDAO
    private lateinit var db: StockDB
    /*private lateinit var repo: RoomRepositoryBought*/

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()


        /*repo = RoomRepositoryBought(application)*/
        db = Room.inMemoryDatabaseBuilder(
            context.applicationContext,
            StockDB::class.java,
        ).build()
        userDao = db.stockBoughtDAO
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeUserAndReadInList() {

        userDao.insert(*StockBlock.list.toTypedArray())
        val all = userDao.getAll()
        assertThat(all, equalTo(StockBlock.list))
    }
}

object StockBlock{
    val list = mutableListOf<StockBought>()

    init {
        list.add(StockBought("A", 5.243, 8.461, 6.014))
        list.add(StockBought("B", 5.2, 3.1, 4.5))
        list.add(StockBought("C", 1.09, 2.09, 3.09))
        list.add(StockBought("D", 6.0, 4.98, 0.002))
        list.add(StockBought("E", 257.568, 1.0, 2.0))
    }


}