package com.foivos.mycryptoapp.data.local.mappers

import com.foivos.mycryptoapp.data.local.CoinEntity
import com.foivos.mycryptoapp.domain.model.Coin

fun CoinEntity.toCoin(): Coin {
    return Coin(
        id = id,
        isActive = isActive,
        name = name,
        rank = rank,
        symbol = symbol
    )
}