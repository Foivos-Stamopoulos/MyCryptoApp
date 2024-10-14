package com.foivos.mycryptoapp.presentation.coin_list

import com.foivos.mycryptoapp.domain.model.Coin

data class CoinListState(
    val isLoading: Boolean = true,
    val favoriteCoins: List<Coin> = emptyList(),
    val coins: List<Coin> = emptyList()
)
