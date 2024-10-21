package com.foivos.mycryptoapp.presentation.coin_detail

import androidx.lifecycle.SavedStateHandle
import com.foivos.mycryptoapp.Constants
import com.foivos.mycryptoapp.domain.model.CoinDetail
import com.foivos.mycryptoapp.domain.model.TeamMember
import com.foivos.mycryptoapp.domain.repository.CoinRepository
import com.foivos.mycryptoapp.domain.util.DataError
import com.foivos.mycryptoapp.domain.util.Result
import com.foivos.mycryptoapp.presentation.util.UiText
import com.foivos.mycryptoapp.presentation.util.asUiText
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CoinDetailViewModelTest {

    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()

    private lateinit var viewModel: CoinDetailViewModel
    private lateinit var coinRepository: CoinRepository

    @BeforeEach
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        coinRepository = mockk()
    }

    @Test
    fun `when coinId is null, fetchCoinDetail is not called`() = runTest {
        // Given: SavedStateHandle contains no coinId
        val savedStateHandle = mockk<SavedStateHandle>()
        every { savedStateHandle.get<String>(Constants.PARAM_COIN_ID) } returns null

        // When: the ViewModel is initialized
        viewModel = CoinDetailViewModel(coinRepository, savedStateHandle)

        // Then: fetchCoinDetail is not called
        coVerify(exactly = 0) { coinRepository.fetchCoinDetail(any()) }

    }

    @Test
    fun `fetchCoinDetail updates state with success`() = runTest {
        // Given: CoinId exists and repository returns CoinDetail
        val coinId = "1"
        val savedStateHandle = mockk<SavedStateHandle>()
        every { savedStateHandle.get<String>(Constants.PARAM_COIN_ID) } returns coinId
        coEvery { coinRepository.fetchCoinDetail(coinId) } returns Result.Success(coinDetail)

        // When: the ViewModel is initialized
        viewModel = CoinDetailViewModel(coinRepository, savedStateHandle)

        // Then: Verify the state is updated with the coin details and isLoading is set to false
        assertFalse(viewModel.state.isLoading)
        assertEquals(coinDetail, viewModel.state.coinDetail)
    }

    @Test
    fun `fetchCoinDetail updates state with error and sends event`() = runTest {
        // Given: Mock an error response from the repository
        val coinId = "1"
        val savedStateHandle = mockk<SavedStateHandle>()
        every { savedStateHandle.get<String>(Constants.PARAM_COIN_ID) } returns coinId
        coEvery { coinRepository.fetchCoinDetail(coinId) } returns Result.Error(DataError.Network.UNKNOWN)

        // When: The ViewModel is initialized and fetchCoinDetail is called
        viewModel = CoinDetailViewModel(coinRepository, savedStateHandle)

        // Then: Verify the state is updated with isLoading = false
        assertFalse(viewModel.state.isLoading)

        // And: Verify that an error event is emitted
        val event = viewModel.events.first()
        assertTrue(event is CoinDetailEvent.Error)
        assertEquals(
            (DataError.Network.UNKNOWN.asUiText() as UiText.StringResource).id,
            ((event as CoinDetailEvent.Error).error as UiText.StringResource).id
        )
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private val coinDetail = CoinDetail(
        coinId = "1",
        name = "Bitcoin",
        description = "Bitcoin is lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum",
        symbol = "BTC",
        rank = 1,
        isActive = true,
        tags = listOf(
            "Mining",
            "Cryptocurrency",
            "Payments",
            "Digital currency",
            "Encryption",
            "Virtual accounting system"
        ),
        team = listOf(
            TeamMember(
                id = "1",
                name = "John Smith",
                position = "Founder"
            )
        )
    )

}