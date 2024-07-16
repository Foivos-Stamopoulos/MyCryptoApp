package com.foivos.mycryptoapp.presentation.coin_list

sealed interface CoinListAction {

    data class OnCoinClick(val coinId: String): CoinListAction

}