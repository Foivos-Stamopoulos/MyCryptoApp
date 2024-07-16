package com.foivos.mycryptoapp.data.remote

import com.foivos.mycryptoapp.data.remote.mappers.toCoin
import com.foivos.mycryptoapp.domain.data_source.RemoteCoinDataSource
import com.foivos.mycryptoapp.domain.model.Coin
import com.foivos.mycryptoapp.domain.util.DataError
import com.foivos.mycryptoapp.domain.util.Result
import javax.inject.Inject

class RetrofitRemoteCoinDataSource @Inject constructor(
    private val api: CoinPaprikaApi
) : RemoteCoinDataSource {

    override suspend fun fetchCoins(): Result<List<Coin>, DataError.Network> {
        try {
            val result = api.fetchCoins()
            return Result.Success(result.map { it.toCoin() })
        } catch (exception: Exception) {

        }
        TODO()
    }

    override suspend fun fetchCoinById(coinId: String): Result<Coin, DataError.Network> {
        TODO("Not yet implemented")
    }

}