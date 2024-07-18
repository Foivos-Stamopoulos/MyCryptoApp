package com.foivos.mycryptoapp.domain.repository

import com.foivos.mycryptoapp.domain.model.Coin
import com.foivos.mycryptoapp.domain.util.DataError
import kotlinx.coroutines.flow.Flow

interface CoinRepository {

    fun getCoins(): Flow<List<Coin>>

    //suspend fun getCoinDetail(): Result<Coin, DataError.Local>

}