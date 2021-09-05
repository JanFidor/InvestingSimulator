package com.example.investingsimulator

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.investingsimulator.room.bought.StockBoughtRoom
import com.example.investingsimulator.room.bought.StockBoughtDAO
import com.example.investingsimulator.room.templates.StockDB
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
    val list = mutableListOf<StockBoughtRoom>()

    init {
        /*list.add(StockBoughtRoom("A", 5.243, 8.461))
        list.add(StockBoughtRoom("B", 5.2, 3.1))
        list.add(StockBoughtRoom("C", 1.09, 2.09))
        list.add(StockBoughtRoom("D", 6.0, 4.98))
        list.add(StockBoughtRoom("E", 257.568, 1.0))*/
    }


}