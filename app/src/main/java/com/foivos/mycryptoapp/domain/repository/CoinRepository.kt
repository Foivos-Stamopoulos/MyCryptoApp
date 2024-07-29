package com.foivos.mycryptoapp.domain.repository

import com.foivos.mycryptoapp.domain.model.Coin
import com.foivos.mycryptoapp.domain.model.CoinDetail
import com.foivos.mycryptoapp.domain.util.DataError
import com.foivos.mycryptoapp.domain.util.Result
import kotlinx.coroutines.flow.Flow

interface CoinRepository {

    fun getCoins(): Flow<List<Coin>>

    suspend fun fetchCoins(): Result<Unit, DataError>

    suspend fun fetchCoinDetail(coinId: String): Result<CoinDetail, DataError.Network>

}