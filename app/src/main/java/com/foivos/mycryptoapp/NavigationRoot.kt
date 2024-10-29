package com.foivos.mycryptoapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.foivos.mycryptoapp.presentation.coin_detail.CoinDetailScreenRoot
import com.foivos.mycryptoapp.presentation.coin_list.CoinListScreenRoot
import com.foivos.mycryptoapp.presentation.util.Graph
import com.foivos.mycryptoapp.presentation.util.Screen

@Composable
fun NavigationRoot(
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = Graph.Coin.route
    ) {
        coinGraph(navController)
    }

}

private fun NavGraphBuilder.coinGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = Screen.CoinListScreen.route,
        route = Graph.Coin.route
    ) {
        composable(route = Screen.CoinListScreen.route) {
            CoinListScreenRoot(
                onCoinClick = {
                    navController.navigate(route = Screen.CoinDetailScreen.route + "/{${Constants.PARAM_COIN_ID}}"
                        .replace(
                            oldValue = "{${Constants.PARAM_COIN_ID}}",
                            newValue = it
                        ))
                }
            )
        }
        composable(
            route = Screen.CoinDetailScreen.route + "/{${Constants.PARAM_COIN_ID}}"
        ) {
            CoinDetailScreenRoot(
                onBackClick = {
                    navController.navigateUp()
                }
            )
        }
    }

}