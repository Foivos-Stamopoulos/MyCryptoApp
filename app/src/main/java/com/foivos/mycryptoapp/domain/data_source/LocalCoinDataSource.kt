package com.foivos.mycryptoapp.domain.data_source

import com.foivos.mycryptoapp.domain.model.Coin
import com.foivos.mycryptoapp.domain.util.DataError
import com.foivos.mycryptoapp.domain.util.Result
import kotlinx.coroutines.flow.Flow

interface LocalCoinDataSource {

    fun getCoins(): Flow<List<Coin>>

    suspend fun upsertCoin(coin: Coin): Result<String, DataError.Local>

    suspend fun upsertCoins(coins: List<Coin>): Result<List<String>, DataError.Local>
}