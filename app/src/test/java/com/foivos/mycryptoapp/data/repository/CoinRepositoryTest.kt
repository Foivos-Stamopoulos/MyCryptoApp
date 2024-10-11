package com.foivos.mycryptoapp.data.repository

import com.foivos.mycryptoapp.data.remote.dto.CoinDetailDto
import com.foivos.mycryptoapp.data.remote.mappers.toCoinDetail
import com.foivos.mycryptoapp.domain.data_source.LocalCoinDataSource
import com.foivos.mycryptoapp.domain.data_source.RemoteCoinDataSource
import com.foivos.mycryptoapp.domain.model.Coin
import com.foivos.mycryptoapp.domain.util.DataError
import com.foivos.mycryptoapp.domain.util.Result
import com.google.gson.Gson
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class CoinRepositoryTest {

    private val localCoinDataSource = mockk<LocalCoinDataSource>()
    private val remoteCoinDataSource = mockk<RemoteCoinDataSource>()
    private val repository = CoinRepositoryImpl(localCoinDataSource, remoteCoinDataSource)

    /**
     * Tests for getCoins
     */
    @Test
    fun `getCoins returns a flow of coins`() = runTest {
        // Given
        val coins = listOf(
            Coin(id = "1", isActive = true, name = "Bitcoin", rank = 0, symbol = "BTC"),
            Coin(id = "2", isActive = false, name = "Solana", rank = 1, symbol = "SLN")
        )
        every { localCoinDataSource.getCoins() }.returns(flow { emit(coins) })

        // When
        val result = repository.getCoins().first()

        // Then
        assertEquals(2, result.size)
        verify(exactly = 1) { localCoinDataSource.getCoins() }
    }

    /**
     * Tests for fetchCoins
     */
    @Test
    fun `fetchCoins fetches coins from Api and inserts-updates them in DB`() = runTest {
        // Given
        val coins = listOf(
            Coin(id = "1", isActive = true, name = "Bitcoin", rank = 0, symbol = "BTC"),
            Coin(id = "2", isActive = false, name = "Solana", rank = 1, symbol = "SLN")
        )
        coEvery { remoteCoinDataSource.fetchCoins() }.returns(Result.Success(coins))
        coEvery { localCoinDataSource.upsertCoins(coins) }.returns(Result.Success(listOf("1", "2")))


        // When
        repository.fetchCoins()

        // Then
        coVerify(exactly = 1) { remoteCoinDataSource.fetchCoins() }
        coVerify(exactly = 1) { localCoinDataSource.upsertCoins(coins) }
    }

    @Test
    fun `fetchCoins returns network error when Api call fails`() = runTest {
        // Given
        coEvery { remoteCoinDataSource.fetchCoins() }.returns(Result.Error(DataError.Network.UNKNOWN))

        // When
        val result = repository.fetchCoins()

        // Then
        coVerify(exactly = 1) { remoteCoinDataSource.fetchCoins() }
        assertTrue(result is Result.Error)
        assertEquals(DataError.Network.UNKNOWN, (result as Result.Error).error)
    }


    /**
     * Tests for fetchCoinDetail
     */
    @Test
    fun `fetchCoinDetail returns CoinDetail`() = runTest {
        // Given
        val coinId = "btc-bitcoin"
        val mockResponseJson = ClassLoader.getSystemResource("api_response/coin_detail.json").readText()
        val expectedResponse = Gson().fromJson(mockResponseJson, CoinDetailDto::class.java).toCoinDetail()
        coEvery { remoteCoinDataSource.fetchCoinById(coinId) }.returns(Result.Success(expectedResponse))

        // When
        val result = repository.fetchCoinDetail(coinId)

        // Then
        coVerify(exactly = 1) { remoteCoinDataSource.fetchCoinById(coinId) }
        assertTrue(result is Result.Success)
        assertEquals(expectedResponse, (result as Result.Success).data)
    }

    @Test
    fun `fetchCoinDetail returns network error when Api call fails`() = runTest {
        // Given
        val coinId = "btc-bitcoin"
        coEvery { remoteCoinDataSource.fetchCoinById(coinId) }.returns(Result.Error(DataError.Network.UNKNOWN))

        // When
        val result = repository.fetchCoinDetail(coinId)

        // Then
        coVerify(exactly = 1) { remoteCoinDataSource.fetchCoinById(coinId) }
        assertTrue(result is Result.Error)
        assertEquals(DataError.Network.UNKNOWN, (result as Result.Error).error)
    }

}