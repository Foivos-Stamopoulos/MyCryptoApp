package com.foivos.mycryptoapp.domain.data_source

import com.foivos.mycryptoapp.data.remote.dto.CoinDetailDto
import com.foivos.mycryptoapp.data.remote.dto.CoinDto
import com.foivos.mycryptoapp.domain.util.DataError
import com.foivos.mycryptoapp.domain.util.Result

interface RemoteCoinDataSource {

    suspend fun fetchCoins(): Result<List<CoinDto>, DataError.Network>

    suspend fun fetchCoinById(coinId: String): Result<CoinDetailDto, DataError.Network>

}