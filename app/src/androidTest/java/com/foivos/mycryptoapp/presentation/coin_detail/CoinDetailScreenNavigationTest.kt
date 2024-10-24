package com.foivos.mycryptoapp.presentation.coin_detail

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.espresso.Espresso
import com.foivos.mycryptoapp.HiltTestActivity
import com.foivos.mycryptoapp.R
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
class CoinDetailScreenNavigationTest {

    @get:Rule(order = 1)
    val hiltTestRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    val composeTestRule = createAndroidComposeRule<HiltTestActivity>()

    private lateinit var fakeCoinRepository: FakeCoinRepositoryImpl
    private lateinit var navController: TestNavHostController

    @Before
    fun setup() {
        hiltTestRule.inject()
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
    fun pressing_device_back_button_from_CoinDetailsScreen_navigates_to_CoinListScreen(){
        val coin = coins.first()
        composeTestRule.onNodeWithText(coin.displayName).performClick()
        Espresso.pressBack()
        navController.assertCurrentRouteName(Screen.CoinListScreen.route)
    }

    @Test
    fun pressing_toolbar_back_arrow_from_CoinDetailsScreen_navigates_to_CoinListScreen(){
        val coin = coins.first()
        composeTestRule.onNodeWithText(coin.displayName).performClick()
        composeTestRule.onNodeWithContentDescription(composeTestRule.activity.getString(R.string.content_description_go_back)).performClick()
        navController.assertCurrentRouteName(Screen.CoinListScreen.route)
    }

}