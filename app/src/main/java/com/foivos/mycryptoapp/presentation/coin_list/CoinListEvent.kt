package com.foivos.mycryptoapp.presentation.coin_list

import com.foivos.mycryptoapp.presentation.ui.util.UiText

sealed interface CoinListEvent {

    data class Error(val error: UiText): CoinListEvent

}