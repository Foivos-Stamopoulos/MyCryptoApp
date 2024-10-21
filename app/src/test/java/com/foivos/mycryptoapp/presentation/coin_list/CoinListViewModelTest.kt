package com.foivos.mycryptoapp.presentation.coin_list

import com.foivos.mycryptoapp.domain.model.Coin
import com.foivos.mycryptoapp.domain.repository.CoinRepository
import com.foivos.mycryptoapp.domain.util.DataError
import com.foivos.mycryptoapp.domain.util.Result
import com.foivos.mycryptoapp.presentation.util.UiText
import com.foivos.mycryptoapp.presentation.util.asUiText
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CoinListViewModelTest {

    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()

    private lateinit var viewModel: CoinListViewModel
    private lateinit var coinRepository: CoinRepository

    @BeforeEach
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        coinRepository = mockk()
    }

    @Test
    fun `initially set loading state`() = runTest {
        // Given: The repository emits some coins
        every { coinRepository.getCoins() } returns flowOf(coins).onEach { delay(100) }
        coEvery { coinRepository.fetchCoins() } returns Result.Success(Unit)

        // When: the ViewModel is started
        viewModel = CoinListViewModel(coinRepository)

        // Then: isLoading is true initially
        Assertions.assertTrue(viewModel.state.isLoading)
    }

    @Test
    fun `load and display coins list`() = runTest {
        // Given: The repository emits some coins
        every { coinRepository.getCoins() } returns flowOf(coins)
        coEvery { coinRepository.fetchCoins() } returns Result.Success(Unit)

        // When: the ViewModel is started
        viewModel = CoinListViewModel(coinRepository)

        // Then: the state is updated with the coins and isLoading is false
        Assertions.assertEquals(coins, viewModel.state.coins)
        Assertions.assertFalse(viewModel.state.isLoading)
    }

    @Test
    fun `fetchCoins error stops loading state and emits event`() = runTest {
        // Given: fetchCoins will return an error
        every { coinRepository.getCoins() } returns flowOf(emptyList())
        coEvery { coinRepository.fetchCoins() } returns Result.Error(DataError.Network.UNKNOWN)

        // When: the ViewModel is started
        viewModel = CoinListViewModel(coinRepository)

        // Then: the state is updated with isLoading = false
        Assertions.assertFalse(viewModel.state.isLoading)

        // And: an error event is emitted
        val event  = viewModel.events.first()
        Assertions.assertTrue(event is CoinListEvent.Error)
        Assertions.assertEquals(
            (DataError.Network.UNKNOWN.asUiText() as UiText.StringResource).id,
            ((event as CoinListEvent.Error).error as UiText.StringResource).id
        )
    }

    @AfterEach
    fun afterEach() {
        Dispatchers.resetMain()
    }

    private val coins = listOf(
        Coin(
            id = "btc-bitcoin",
            isActive = true,
            name = "Bitcoin",
            rank = 0,
            symbol = "BTC",
            displayName = "Bitcoin (BTC)"
        )
    )

}