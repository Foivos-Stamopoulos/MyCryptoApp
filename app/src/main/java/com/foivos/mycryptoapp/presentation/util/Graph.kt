package com.foivos.mycryptoapp.presentation.util

sealed class Graph(val route: String) {
    data object Coin: Graph("coin")
}