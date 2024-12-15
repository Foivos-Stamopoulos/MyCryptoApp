package com.foivos.mycryptoapp.data.repository

import com.foivos.mycryptoapp.data.remote.mappers.toCoin
import com.foivos.mycryptoapp.data.remote.mappers.toCoinDetail
import com.foivos.mycryptoapp.domain.data_source.LocalCoinDataSource
import com.foivos.mycryptoapp.domain.data_source.RemoteCoinDataSource
import com.foivos.mycryptoapp.domain.model.Coin
import com.foivos.mycryptoapp.domain.model.CoinDetail
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

    override suspend fun fetchCoins(): EmptyResult<DataError> {
        return when (val result = remoteCoinDataSource.fetchCoins()) {
            is Result.Error -> {
                result
            }
            is Result.Success -> {
                localCoinDataSource.upsertCoins(result.data.map { it.toCoin() }).asEmptyDataResult()
            }
        }
    }

    override suspend fun fetchCoinDetail(coinId: String): Result<CoinDetail, DataError.Network> {
        return when (val result = remoteCoinDataSource.fetchCoinById(coinId)) {
            is Result.Error -> {
                Result.Error(result.error)
            }
            is Result.Success -> {
                Result.Success(result.data.toCoinDetail())
            }
        }
    }
}