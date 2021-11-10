package com.peter.pretest.data.source.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.peter.pretest.data.Source
import com.peter.pretest.data.source.local.PretestDatabase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class PretestDatabaseDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: PretestDatabase
    private lateinit var dao: PretestDatabaseDao

    /**
     *
     * Creating Room DataBase for test purpose
     */

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            PretestDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.pretestDatabaseDao
    }

    /**
     *
     * clear the room database
     */

    @After
    fun tearDown() {
        database.close()
    }

    /**
     *
     * Test the insert function here
     */

    @Test
    fun insetAll() = runBlockingTest {
        val currencies = listOf(
            Source("BTC", "Bitcoin", "BTC"), Source("ETH", "Ethereum", "ETH"),
            Source("XRP", "XRP", "XRP")
        )
        dao.insertAll(currencies)

        val allCurrencies = dao.getAllSortedCurrencies()

        Truth.assertThat(allCurrencies).isEqualTo(currencies)
    }

    /**
     *
     * Test the delete function here
     */

    @Test
    fun deleteAll() = runBlockingTest {
        val currencies = listOf(
            Source("BTC", "Bitcoin", "BTC"), Source("ETH", "Ethereum", "ETH"),
            Source("XRP", "XRP", "XRP")
        )
        dao.insertAll(currencies)

        dao.clear()

        val allCurrencies = dao.getAllSortedCurrencies()

        Truth.assertThat(allCurrencies).isEmpty()
    }


}