package com.foivos.mycryptoapp.data.repository

import com.foivos.mycryptoapp.domain.model.Coin
import com.foivos.mycryptoapp.domain.model.CoinDetail
import com.foivos.mycryptoapp.domain.repository.CoinRepository
import com.foivos.mycryptoapp.domain.util.DataError
import com.foivos.mycryptoapp.domain.util.EmptyResult
import com.foivos.mycryptoapp.domain.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeCoinRepository : CoinRepository {

    private val coins = mutableListOf<Coin>()

    override fun getCoins(): Flow<List<Coin>> {
        return flow {
            emit(coins)
        }
    }

    override suspend fun fetchCoins(): EmptyResult<DataError> {
        TODO("Not yet implemented")
    }

    override suspend fun fetchCoinDetail(coinId: String): Result<CoinDetail, DataError.Network> {
        TODO("Not yet implemented")
    }

    private val mockCoinList = Result.Success(listOf(
        Coin(
            id = "btc-bitcoin",
            isActive = true,
            name = "Bitcoin",
            rank = 0,
            symbol = "BTC"
        ),
        Coin(
            id = "tether-hermionegrangerclintonamberamyrose9inu",
            isActive = false,
            name = "HermioneGrangerClintonAmberAmyRose9Inu",
            rank = 0,
            symbol = "TETHER"
        )
    ))


}