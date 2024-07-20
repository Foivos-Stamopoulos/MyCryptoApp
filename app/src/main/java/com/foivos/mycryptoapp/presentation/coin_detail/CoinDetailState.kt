package com.foivos.mycryptoapp.presentation.coin_detail

import com.foivos.mycryptoapp.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coinDetail: CoinDetail? = null
)