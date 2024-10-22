package com.foivos.mycryptoapp.presentation.coin_list

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.foivos.mycryptoapp.R
import com.foivos.mycryptoapp.data.di.FakeCoinRepositoryImpl
import com.foivos.mycryptoapp.data.di.coins
import com.foivos.mycryptoapp.presentation.MainActivity
import com.foivos.mycryptoapp.presentation.ui.theme.MyCryptoAppTheme
import com.foivos.mycryptoapp.presentation.util.TestTags
import com.foivos.mycryptoapp.util.onNodeWithStringId
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class CoinListScreenTest {

    @get:Rule(order = 1)
    val hiltTestRule = HiltAndroidRule(this)

    @get: Rule(order = 2)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private lateinit var fakeCoinRepository: FakeCoinRepositoryImpl

    @Before
    fun setup() {
        hiltTestRule.inject()
        fakeCoinRepository = FakeCoinRepositoryImpl()
    }

    @Test
    fun verify_content() {
        fakeCoinRepository.setShouldReturnNetworkError(false)

        val viewModel = CoinListViewModel(fakeCoinRepository)

        composeTestRule.activity.setContent {
            MyCryptoAppTheme {
                CoinListScreenRoot(
                    onCoinClick = {},
                    viewModel = viewModel
                )
            }
        }

        composeTestRule.onNodeWithTag(TestTags.COIN_LIST).assertIsDisplayed()

        composeTestRule.onNodeWithStringId(R.string.app_name).assertIsDisplayed()

        val moneyIconContentDescription = composeTestRule.activity.getString(R.string.content_description_money)
        composeTestRule.onNodeWithContentDescription(moneyIconContentDescription).assertIsDisplayed()

        coins.forEach { item ->
            composeTestRule.onNodeWithText(item.displayName).assertIsDisplayed()

            if (item.isActive) {
                composeTestRule.onNodeWithStringId(R.string.active_coin).assertIsDisplayed()
            } else {
                composeTestRule.onNodeWithStringId(R.string.inactive_coin).assertIsDisplayed()
            }
        }
    }

    @Test
    fun display_message_on_network_request_error() {
        fakeCoinRepository.setShouldReturnNetworkError(true)

        val viewModel = CoinListViewModel(fakeCoinRepository)

        composeTestRule.activity.setContent {
            MyCryptoAppTheme {
                CoinListScreenRoot(
                    onCoinClick = {},
                    viewModel = viewModel
                )
            }
        }

        composeTestRule.onNodeWithStringId(R.string.error_unknown).assertIsDisplayed()
    }

}