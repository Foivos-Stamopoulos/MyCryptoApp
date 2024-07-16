package com.foivos.mycryptoapp.data.local

import com.foivos.mycryptoapp.data.local.mappers.toCoin
import com.foivos.mycryptoapp.domain.data_source.LocalCoinDataSource
import com.foivos.mycryptoapp.domain.model.Coin
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RoomLocalCoinDataSource @Inject constructor(
    private val coinDao: CoinDao
) : LocalCoinDataSource {

    override suspend fun getCoins(): Flow<List<Coin>> {
        return flow {
            emit(coinDao.getCoins().map {
                it.toCoin()
            })
        }
    }


}