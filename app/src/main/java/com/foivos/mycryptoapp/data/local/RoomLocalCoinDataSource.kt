package com.foivos.mycryptoapp.data.local

import android.database.sqlite.SQLiteFullException
import com.foivos.mycryptoapp.data.local.mappers.toCoin
import com.foivos.mycryptoapp.data.local.mappers.toCoinEntity
import com.foivos.mycryptoapp.domain.data_source.LocalCoinDataSource
import com.foivos.mycryptoapp.domain.model.Coin
import com.foivos.mycryptoapp.domain.util.DataError
import com.foivos.mycryptoapp.domain.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RoomLocalCoinDataSource @Inject constructor(
    private val coinDao: CoinDao
) : LocalCoinDataSource {

    override fun getCoins(): Flow<List<Coin>> {
        return flow {
            emit(coinDao.getCoins().map {
                it.toCoin()
            })
        }
    }

    override suspend fun upsertCoin(coin: Coin): Result<String, DataError.Local> {
        return try {
            val coinEntity = coin.toCoinEntity()
            coinDao.upsertCoin(coinEntity)
            Result.Success(coinEntity.id)
        } catch (e: SQLiteFullException) {
            Result.Error(DataError.Local.STORAGE_FULL)
        }
    }

    override suspend fun upsertCoins(coins: List<Coin>): Result<List<String>, DataError.Local> {
        return try {
            val coinEntities = coins.map { it.toCoinEntity() }
            coinDao.upsertCoins(coinEntities)
            Result.Success(coinEntities.map { it.id })
        } catch (e: Exception) {
            Result.Error(DataError.Local.STORAGE_FULL)
        }
    }
}