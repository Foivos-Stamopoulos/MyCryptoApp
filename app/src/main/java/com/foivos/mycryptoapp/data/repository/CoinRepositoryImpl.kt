package com.foivos.mycryptoapp.data.repository

import com.foivos.mycryptoapp.domain.data_source.LocalCoinDataSource
import com.foivos.mycryptoapp.domain.data_source.RemoteCoinDataSource
import com.foivos.mycryptoapp.domain.model.Coin
import com.foivos.mycryptoapp.domain.repository.CoinRepository
import com.foivos.mycryptoapp.domain.util.DataError
import com.foivos.mycryptoapp.domain.util.EmptyResult
import com.foivos.mycryptoapp.domain.util.Result
import com.foivos.mycryptoapp.domain.util.asEmptyDataResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val localCoinDataSource: LocalCoinDataSource,
    private val remoteCoinDataSource: RemoteCoinDataSource
): CoinRepository {

    override fun getCoins(): Flow<List<Coin>> {
        return localCoinDataSource.getCoins()
    }

    override suspend fun fetchCoins(): Result<Unit, DataError> {
        return when (val result = remoteCoinDataSource.fetchCoins()) {
            is Result.Error -> {
                result
            }
            is Result.Success -> {
                localCoinDataSource.upsertCoins(result.data)
                Result.Success(Unit)
            }
        }
    }

}