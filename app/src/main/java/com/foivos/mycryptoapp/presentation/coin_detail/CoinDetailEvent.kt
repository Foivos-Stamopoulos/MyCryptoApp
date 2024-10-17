package com.foivos.mycryptoapp.presentation.coin_detail

import com.foivos.mycryptoapp.presentation.ui.util.UiText

sealed interface CoinDetailEvent {
    data class Error(val error: UiText): CoinDetailEvent
}