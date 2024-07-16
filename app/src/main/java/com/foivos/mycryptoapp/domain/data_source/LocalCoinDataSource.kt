package com.foivos.mycryptoapp.domain.data_source

import com.foivos.mycryptoapp.domain.model.Coin
import kotlinx.coroutines.flow.Flow

interface LocalCoinDataSource {

    suspend fun getCoins(): Flow<List<Coin>>

}