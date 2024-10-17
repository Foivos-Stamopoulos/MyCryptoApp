package com.foivos.mycryptoapp.presentation.coin_detail

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.lifecycle.SavedStateHandle
import com.foivos.mycryptoapp.Constants
import com.foivos.mycryptoapp.R
import com.foivos.mycryptoapp.data.di.FakeCoinRepositoryImpl
import com.foivos.mycryptoapp.presentation.MainActivity
import com.foivos.mycryptoapp.presentation.ui.theme.MyCryptoAppTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.first
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class CoinDetailScreenTest {

    @get: Rule(order = 1)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

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

        composeTestRule.activity.setContent {
            MyCryptoAppTheme {
                CoinDetailScreenRoot(
                    onBackClick = {},
                    viewModel)
            }
        }

        composeTestRule.onNodeWithTag(COIN_TITLE_TAG).assertIsDisplayed()
        composeTestRule.onNodeWithTag(COIN_DESCRIPTION_TAG).assertIsDisplayed()
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.tags)).assertIsDisplayed()
        composeTestRule.onNodeWithText("Mining").assertIsDisplayed()
        composeTestRule.onNodeWithText("Cryptocurrency").assertIsDisplayed()
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.team_members)).assertIsDisplayed()
        composeTestRule.onNodeWithText("John Smith").assertIsDisplayed()
        composeTestRule.onNodeWithText("Founder").assertIsDisplayed()
        composeTestRule.onNodeWithText("Juliana Smith").assertIsDisplayed()
        composeTestRule.onNodeWithText("Founder").assertIsDisplayed()
    }

    @Test
    fun display_error_message_when_network_call_fails() {
        fakeCoinRepository.setShouldReturnNetworkError(true)

        val savedStateHandle = SavedStateHandle()
        savedStateHandle[Constants.PARAM_COIN_ID] = "1"
        val viewModel = CoinDetailViewModel(fakeCoinRepository, savedStateHandle)

        composeTestRule.activity.setContent {
            MyCryptoAppTheme {
                CoinDetailScreenRoot(onBackClick = {}, viewModel)
            }
        }

        composeTestRule.onNodeWithTag(COIN_DETAIL_CONTENT_TAG).assertDoesNotExist()

        val error = composeTestRule.activity.getString(R.string.error_unknown)
        composeTestRule.onNodeWithText(error).assertIsDisplayed()

    }


}