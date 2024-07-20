package com.foivos.mycryptoapp

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.foivos.mycryptoapp.presentation.coin_detail.CoinDetailScreenRoot
import com.foivos.mycryptoapp.presentation.coin_list.CoinListScreenRoot

@Composable
fun NavigationRoot(
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = "coin"
    ) {
        coinGraph(navController)
    }

}

private fun NavGraphBuilder.coinGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = "coin_list",
        route = "coin"
    ) {
        composable(
            route = "coin_list"
        ) {
            CoinListScreenRoot(
                onCoinClick = {
                    navController.navigate(route = "coin_detail/$it")
                }
            )
        }
        composable(
            route = "coin_detail" + "/{coinId}"
        ) {
            CoinDetailScreenRoot(
                onBackClick = {
                    navController.navigateUp()
                }
            )
        }
    }

}