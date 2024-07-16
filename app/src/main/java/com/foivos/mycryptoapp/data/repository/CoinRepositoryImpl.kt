package com.foivos.mycryptoapp.data.repository

import com.foivos.mycryptoapp.domain.data_source.LocalCoinDataSource
import com.foivos.mycryptoapp.domain.data_source.RemoteCoinDataSource
import com.foivos.mycryptoapp.domain.model.Coin
import com.foivos.mycryptoapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val localCoinDataSource: LocalCoinDataSource,
    private val remoteCoinDataSource: RemoteCoinDataSource
): CoinRepository {

    override suspend fun getCoins(): Flow<List<Coin>> {
        TODO("Not yet implemented")
    }

}