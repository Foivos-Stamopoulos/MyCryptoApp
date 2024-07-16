package com.foivos.mycryptoapp.data.remote

import com.foivos.mycryptoapp.data.remote.dto.CoinDetailDto
import com.foivos.mycryptoapp.data.remote.dto.CoinDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinPaprikaApi {

    @GET("/v1/coins")
    suspend fun fetchCoins(): List<CoinDto>

    @GET("/v1/coins/{coinId}")
    suspend fun fetchCoinById(
        @Path("coinId") coinId: String
    ): CoinDetailDto

}