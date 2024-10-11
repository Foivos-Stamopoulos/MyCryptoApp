package com.foivos.mycryptoapp.data.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import app.cash.turbine.test
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class CoinDaoTest {

    @get: Rule
    val dispatcherRule = TestDispatcherRule()

    private lateinit var coinDao: CoinDao
    private lateinit var coinDB: CoinDatabase

    @Before
    fun create() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        coinDB = Room.inMemoryDatabaseBuilder(context, CoinDatabase::class.java).build()
        coinDao = coinDB.dao
    }

    @After
    fun cleanup() {
        coinDB.close()
    }

    @Test
    fun upsertCoin_should_return_the_coin_inFlow() = runTest {
        val coin1 = CoinEntity(id = "1", isActive = true, name = "Bitcoin", rank = 0, symbol = "")
        val coin2 = CoinEntity(id = "2", isActive = true, name = "Solana", rank = 0, symbol = "")
        coinDao.upsertCoin(coin1)
        coinDao.upsertCoin(coin2)

        coinDao.getCoins().test {
            val list = awaitItem()
            assert(list.contains(coin1))
            assert(list.contains(coin2))
            cancel()
        }
    }

    @Test
    fun upsertCoins_should_return_the_coins_inFlow() = runTest {
        val coin1 = CoinEntity(id = "1", isActive = true, name = "Bitcoin", rank = 0, symbol = "")
        val coin2 = CoinEntity(id = "2", isActive = true, name = "Solana", rank = 0, symbol = "")
        val coins = listOf(coin1, coin2)
        coinDao.upsertCoins(coins)

        coinDao.getCoins().test {
            val list = awaitItem()
            assert(list.contains(coin1))
            assert(list.contains(coin2))
            cancel()
        }
    }

}