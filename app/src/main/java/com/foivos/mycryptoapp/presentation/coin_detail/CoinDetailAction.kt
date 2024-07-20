package com.foivos.mycryptoapp.presentation.coin_detail

sealed interface CoinDetailAction {
    data object OnBackClick: CoinDetailAction
}