package com.foivos.mycryptoapp.domain.data_source

import com.foivos.mycryptoapp.domain.model.Coin
import com.foivos.mycryptoapp.domain.util.DataError
import com.foivos.mycryptoapp.domain.util.Result

interface RemoteCoinDataSource {

    suspend fun fetchCoins(): Result<List<Coin>, DataError.Network>

    suspend fun fetchCoinById(coinId: String): Result<Coin, DataError.Network>

}