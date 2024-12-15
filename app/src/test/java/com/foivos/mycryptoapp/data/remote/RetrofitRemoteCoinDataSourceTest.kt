package com.foivos.mycryptoapp.data.remote

import com.foivos.mycryptoapp.data.remote.dto.CoinDetailDto
import com.foivos.mycryptoapp.data.remote.dto.CoinDto
import com.foivos.mycryptoapp.data.remote.dto.LinksDto
import com.foivos.mycryptoapp.data.remote.dto.WhitePaperDto
import com.foivos.mycryptoapp.domain.util.DataError
import com.foivos.mycryptoapp.domain.util.Result
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class RetrofitRemoteCoinDataSourceTest {

    private val api = mockk<CoinPaprikaApi>()
    private val retrofitRemoteCoinDataSource = RetrofitRemoteCoinDataSource(api)
    private val runtimeException = RuntimeException()

    /**
     *  Tests for fetchCoins()
     */

    @Test
    fun `fetchCoins returns list of CoinDto`() = runTest {
        // Given
        val coinDto1 = CoinDto(id = "btc-bitcoin", isActive = true, name = "Bitcoin", rank = 0, symbol = "BTC", isNew = false, type = "")
        val coinDto2 = CoinDto(id = "tether-hermionegrangerclintonamberamyrose9inu", isActive = false, name = "HermioneGrangerClintonAmberAmyRose9Inu", rank = 0, symbol = "TETHER", isNew = false, type = "")
        val coinList = listOf(coinDto1, coinDto2)
        coEvery { api.fetchCoins() }.returns(coinList)

        // When
        val result = retrofitRemoteCoinDataSource.fetchCoins()

        // Then
        assertTrue(result is Result.Success)
        coVerify(exactly = 1) { api.fetchCoins() }
        assertEquals(coinList.size, (result as Result.Success).data.size)
    }

    @Test
    fun `fetchCoins returns error when api request fails`() = runTest {
        // Given
        coEvery { api.fetchCoins() }.throws(runtimeException)

        // When
        val result = retrofitRemoteCoinDataSource.fetchCoins()

        // Then
        assertTrue(result is Result.Error)
        coVerify(exactly = 1) { api.fetchCoins() }
        assertEquals(DataError.Network.UNKNOWN, (result as Result.Error).error)
    }


    /**
     *  Tests for fetchCoinById()
     */

    @Test
    fun `fetchCoinById returns CoinDetailDto`()  = runTest {
        // Given
        val coinId = "1"
        coEvery { api.fetchCoinById(coinId) }.returns(coinDetailDto)

        // When
        val result = retrofitRemoteCoinDataSource.fetchCoinById(coinId)

        // Then
        assertTrue(result is Result.Success)
        coVerify(exactly = 1) { api.fetchCoinById(coinId) }
    }

    @Test
    fun `fetchCoinById returns error when api request fails`()  = runTest {
        // Given
        val coinId = "1"
        coEvery { api.fetchCoinById(coinId) }.throws(runtimeException)

        // When
        val result = retrofitRemoteCoinDataSource.fetchCoinById(coinId)

        // Then
        assertTrue(result is Result.Error)
        coVerify(exactly = 1) { api.fetchCoinById(coinId) }
        assertEquals(DataError.Network.UNKNOWN, (result as Result.Error).error)
    }


    private val coinDetailDto = CoinDetailDto(
        id = "1",
        name = "Bitcoin",
        description = "Bitcoin is lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum",
        symbol = "BTC",
        rank = 1,
        isActive = true,
        isNew = false,
        type = "",
        tags = listOf(),
        team = listOf(),
        message = null,
        openSource = false,
        startedAt = null,
        developmentStatus = null,
        hardwareWallet = true,
        proofType = null,
        orgStructure = null,
        hashAlgorithm = null,
        links = LinksDto(listOf(), listOf(), listOf(), listOf(), listOf(), listOf()),
        linksExtended = listOf(),
        firstDataAt = null,
        lastDataAt = null,
        whitePaperDto = WhitePaperDto(null, null)
        )

}