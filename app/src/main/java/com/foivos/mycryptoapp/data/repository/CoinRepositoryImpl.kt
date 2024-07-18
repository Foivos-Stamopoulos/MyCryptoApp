package com.foivos.mycryptoapp.data.repository

import com.foivos.mycryptoapp.domain.data_source.LocalCoinDataSource
import com.foivos.mycryptoapp.domain.data_source.RemoteCoinDataSource
import com.foivos.mycryptoapp.domain.model.Coin
import com.foivos.mycryptoapp.domain.repository.CoinRepository
import com.foivos.mycryptoapp.domain.util.Result
import com.foivos.mycryptoapp.domain.util.asEmptyDataResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val remoteCoinDataSource: RemoteCoinDataSource
): CoinRepository {

    override fun getCoins(): Flow<List<Coin>> {
        return flow {
            when (val result = remoteCoinDataSource.fetchCoins()) {
                is Result.Error -> {
                    emit(emptyList())
                }
                is Result.Success -> {
                    emit(result.data)
                }
            }
        }
    }

}