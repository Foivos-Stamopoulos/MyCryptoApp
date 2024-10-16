package com.foivos.mycryptoapp.data.remote.mappers

import com.foivos.mycryptoapp.data.remote.dto.CoinDto
import com.foivos.mycryptoapp.domain.model.Coin

fun CoinDto.toCoin(): Coin {
    return Coin(
        id = id,
        isActive = isActive,
        name = name,
        rank = rank,
        symbol = symbol,
        displayName = "$name (${symbol})"
    )
}