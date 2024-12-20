package com.foivos.mycryptoapp.presentation.coin_list

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.foivos.mycryptoapp.HiltTestActivity
import com.foivos.mycryptoapp.data.di.FakeCoinRepositoryImpl
import com.foivos.mycryptoapp.data.di.coins
import com.foivos.mycryptoapp.presentation.MyCryptoApp
import com.foivos.mycryptoapp.presentation.ui.theme.MyCryptoAppTheme
import com.foivos.mycryptoapp.presentation.util.Screen
import com.foivos.mycryptoapp.util.assertCurrentRouteName
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class CoinListScreenNavigationTest {

    @get:Rule(order = 1)
    val hiltAndroidRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    val composeTestRule = createAndroidComposeRule<HiltTestActivity>()

    private lateinit var navController: TestNavHostController
    private lateinit var fakeCoinRepository: FakeCoinRepositoryImpl

    @Before
    fun setup() {
        hiltAndroidRule.inject()
        fakeCoinRepository = FakeCoinRepositoryImpl()
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            MyCryptoAppTheme {
                MyCryptoApp(navController = navController)
            }
        }
    }

    @Test
    fun navHost_verify_that_CoinListScreen_is_the_start_destination() {
        navController.assertCurrentRouteName(Screen.CoinListScreen.route)
    }

    @Test
    fun click_on_a_coin_navigates_to_CoinDetailScreen() {
        val coin = coins.first()
        composeTestRule.onNodeWithText(coin.displayName).performClick()
        navController.assertCurrentRouteName("${Screen.CoinDetailScreen.route}/{coinId}")
    }

}