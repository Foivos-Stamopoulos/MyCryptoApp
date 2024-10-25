package com.foivos.mycryptoapp.presentation.coin_detail

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.lifecycle.SavedStateHandle
import androidx.test.platform.app.InstrumentationRegistry
import com.foivos.mycryptoapp.Constants
import com.foivos.mycryptoapp.HiltTestActivity
import com.foivos.mycryptoapp.R
import com.foivos.mycryptoapp.data.di.FakeCoinRepositoryImpl
import com.foivos.mycryptoapp.presentation.ui.theme.MyCryptoAppTheme
import com.foivos.mycryptoapp.presentation.util.TestTags
import com.foivos.mycryptoapp.util.onNodeWithStringId
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class CoinDetailScreenTest {

    @get: Rule(order = 1)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    val composeTestRule = createAndroidComposeRule<HiltTestActivity>()

    private lateinit var fakeCoinRepository: FakeCoinRepositoryImpl

    @Before
    fun setup() {
        hiltRule.inject()
        fakeCoinRepository = FakeCoinRepositoryImpl()
    }

    @Test
    fun coin_details_are_displayed_successfully() {
        fakeCoinRepository.setShouldReturnNetworkError(false)

        val savedStateHandle = SavedStateHandle()
        savedStateHandle[Constants.PARAM_COIN_ID] = "1"
        val viewModel = CoinDetailViewModel(fakeCoinRepository, savedStateHandle)

        composeTestRule.setContent {
            MyCryptoAppTheme {
                CoinDetailScreenRoot(
                    onBackClick = {},
                    viewModel)
            }
        }

        composeTestRule.onNodeWithTag(TestTags.COIN_TITLE).assertIsDisplayed()
        composeTestRule.onNodeWithTag(TestTags.COIN_DESCRIPTION).assertIsDisplayed()
        composeTestRule.onNodeWithStringId(R.string.tags).assertIsDisplayed()
        composeTestRule.onNodeWithText("Mining").assertIsDisplayed()
        composeTestRule.onNodeWithText("Cryptocurrency").assertIsDisplayed()
        composeTestRule.onNodeWithStringId(R.string.team_members).assertIsDisplayed()
        composeTestRule.onNodeWithText("John Smith").assertIsDisplayed()
        composeTestRule.onNodeWithText("Founder").assertIsDisplayed()
        composeTestRule.onNodeWithText("Juliana Smith").assertIsDisplayed()
        composeTestRule.onNodeWithText("Founder").assertIsDisplayed()
    }

    @Test
    fun display_snackbar_with_error_message_when_network_call_fails() {
        fakeCoinRepository.setShouldReturnNetworkError(true)

        val savedStateHandle = SavedStateHandle()
        savedStateHandle[Constants.PARAM_COIN_ID] = "1"
        val viewModel = CoinDetailViewModel(fakeCoinRepository, savedStateHandle)

        val snackbarHostState = SnackbarHostState()
        composeTestRule.setContent {
            MyCryptoAppTheme {
                CoinDetailScreenRoot(
                    onBackClick = {},
                    viewModel = viewModel,
                    snackbarHostState = snackbarHostState
                )
            }
        }

        composeTestRule.onNodeWithTag(TestTags.COIN_DETAIL_CONTENT).assertDoesNotExist()

        composeTestRule.onNodeWithStringId(R.string.error_unknown).assertIsDisplayed()

        // Then the first message received in the Snackbar is an error message
        runBlocking {
            // snapshotFlow converts a State to a Kotlin Flow so we can observe it
            // wait for the first a non-null `currentSnackbarData`
            val actualSnackbarText = snapshotFlow { snackbarHostState.currentSnackbarData }
                .filterNotNull().first().visuals.message
            val expectedSnackbarText = InstrumentationRegistry.getInstrumentation()
                .targetContext.resources.getString(R.string.error_unknown)
            Assert.assertEquals(expectedSnackbarText, actualSnackbarText)
        }

    }


}