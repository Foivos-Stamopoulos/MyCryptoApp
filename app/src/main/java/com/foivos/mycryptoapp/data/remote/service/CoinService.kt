package com.foivos.mycryptoapp.data.remote.service

import com.foivos.mycryptoapp.data.remote.CoinPaprikaApi
import com.foivos.mycryptoapp.domain.model.Coin
import com.foivos.mycryptoapp.domain.util.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CoinService @Inject constructor(
    val api: CoinPaprikaApi
) {

    /*suspend fun fetchCoins(): Flow<Result<List<Coin>, Nothing>> {
        return try {
            Result.Success(api.fetchCoins())
        } catch (exception: Exception) {
            Result.Error()
        }
    }*/

}