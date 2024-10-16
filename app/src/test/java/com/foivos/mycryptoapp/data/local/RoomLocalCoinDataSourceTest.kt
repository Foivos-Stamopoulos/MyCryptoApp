package com.foivos.mycryptoapp.data.local

import android.database.sqlite.SQLiteFullException
import com.foivos.mycryptoapp.domain.model.Coin
import com.foivos.mycryptoapp.domain.util.DataError
import com.foivos.mycryptoapp.domain.util.Result
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class RoomLocalCoinDataSourceTest {

    private lateinit var coinDao: CoinDao
    private lateinit var roomLocalCoinDataSource: RoomLocalCoinDataSource

    @BeforeEach
    fun setUp() {
        coinDao = mockk()
        roomLocalCoinDataSource = RoomLocalCoinDataSource(coinDao)
    }

    @Test
    fun `getCoins returns flow of coins`() = runTest {

        // Given
        val coinEntity1 = CoinEntity(id = "btc-bitcoin", isActive = true, name = "Bitcoin", rank = 0, symbol = "BTC")
        val coinEntity2 = CoinEntity(id = "tether-hermionegrangerclintonamberamyrose9inu", isActive = false, name = "HermioneGrangerClintonAmberAmyRose9Inu", rank = 0, symbol = "TETHER")
        val coinList = listOf(coinEntity1, coinEntity2)

        every { coinDao.getCoins() } returns flowOf(coinList) // Mock the DAO to return a flow of coin entities


        // When
        val result = roomLocalCoinDataSource.getCoins().first()

        // Then
        assertEquals(2, result.size)

        assertEquals("btc-bitcoin", result[0].id)
        assertEquals("Bitcoin", result[0].name)
        assertEquals(0, result[0].rank)
        assertEquals("BTC", result[0].symbol)
        assertTrue(result[0].isActive)

        assertEquals("tether-hermionegrangerclintonamberamyrose9inu", result[1].id)
        assertEquals("HermioneGrangerClintonAmberAmyRose9Inu", result[1].name)
        assertEquals(0, result[1].rank)
        assertEquals("TETHER", result[1].symbol)
        assertFalse(result[1].isActive)

        verify { coinDao.getCoins() }   // Verify the interaction
    }

    @Test
    fun `upsertCoin returns CoinId when coin insertion is successful`() = runTest {
        // Given
        val coin = Coin(id = "btc-bitcoin", isActive = true, name = "Bitcoin", rank = 0, symbol = "BTC", displayName = "Bitcoin (BTC)")

        coEvery { coinDao.upsertCoin(any()) } just Runs  // Mock the DAO to perform the upsert

        // When
        val result = roomLocalCoinDataSource.upsertCoin(coin)

        // Then
        assertTrue(result is Result.Success)
        assertEquals("btc-bitcoin", (result as Result.Success).data)

        coVerify { coinDao.upsertCoin(any()) } // Verify the interaction
    }

    @Test
    fun `upsertCoin returns error when coin insertion fails`() = runTest {
        // Given
        val coin = Coin(id = "btc-bitcoin", isActive = true, name = "Bitcoin", rank = 0, symbol = "BTC", displayName = "Bitcoin (BTC)")

        coEvery { coinDao.upsertCoin(any()) } throws SQLiteFullException()  // Mock the DAO to throw SQLiteFullException

        // When
        val result = roomLocalCoinDataSource.upsertCoin(coin)

        // Then
        assertTrue(result is Result.Error)
        assertEquals(DataError.Local.STORAGE_FULL, (result as Result.Error).error)

        coVerify { coinDao.upsertCoin(any()) } // Verify the interaction
    }

    @Test
    fun `upsertCoins returns list of CoinIds when insertion is successful`() = runTest {
        // Given
        val bitcoin = Coin(id = "btc-bitcoin", isActive = true, name = "Bitcoin", rank = 0, symbol = "BTC", displayName = "Bitcoin (BTC)")
        val hexDc = Coin(id = "hexdc-hexdc", isActive = true, name = "HEXDC", rank = 0, symbol = "HEXDC", displayName = "HEXDC (HEXDC)")
        val coins = listOf(bitcoin, hexDc)

        coEvery { coinDao.upsertCoins(any()) } just Runs  // Mock the DAO to perform the upsert

        // When
        val result = roomLocalCoinDataSource.upsertCoins(coins)

        // Then
        assertTrue(result is Result.Success)
        val idList = (result as Result.Success).data
        assertEquals("btc-bitcoin", idList[0])
        assertEquals("hexdc-hexdc", idList[1])

        coVerify { coinDao.upsertCoins(any()) } // Verify the interaction
    }

    @Test
    fun `upsertCoins returns error when insertion fails`() = runTest {
        // Given
        val bitcoin = Coin(id = "btc-bitcoin", isActive = true, name = "Bitcoin", rank = 0, symbol = "BTC", displayName = "Bitcoin (BTC)")
        val hexDc = Coin(id = "hexdc-hexdc", isActive = true, name = "HEXDC", rank = 0, symbol = "HEXDC", displayName = "HEXDC (HEXDC)")
        val coins = listOf(bitcoin, hexDc)

        coEvery { coinDao.upsertCoins(any()) } throws SQLiteFullException()  // Mock the DAO to throw SQLiteFullException

        // When
        val result = roomLocalCoinDataSource.upsertCoins(coins)

        // Then
        assertTrue(result is Result.Error)
        assertEquals(DataError.Local.STORAGE_FULL, (result as Result.Error).error)

        coVerify { coinDao.upsertCoins(any()) } // Verify the interaction
    }

}