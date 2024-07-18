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
        /*try {
            val result = api.fetchCoins()
            return Result.Success(result.map { it.toCoin() })
        } catch (exception: Exception) {

        }
        */
        return Result.Success(listOf(
            Coin(
                id = "bitcoinId",
                isActive = true,
                name = "Bitcoin",
                rank = 1,
                symbol = "BTC"
            ),
            Coin(
                id = "tether1Id",
                isActive = false,
                name = "Tether",
                rank = 2,
                symbol = "USDT1"
            ),
            Coin(
                id = "ethereumId",
                isActive = true,
                name = "Ethereum",
                rank = 3,
                symbol = "ETH"
            ),
            Coin(
                id = "cardanoId",
                isActive = false,
                name = "Cardano",
                rank = 4,
                symbol = "ADA"
            ),
            Coin(
                id = "hexId",
                isActive = true,
                name = "Hex",
                rank = 5,
                symbol = "HEX"
            ),
            Coin(
                id = "binanceCoinId",
                isActive = false,
                name = "Binance Coin",
                rank = 6,
                symbol = "BNB"
            ),
            Coin(
                id = "tetherId",
                isActive = false,
                name = "Tether",
                rank = 7,
                symbol = "USDT"
            ),
            Coin(
                id = "xrpId",
                isActive = true,
                name = "XRP",
                rank = 8,
                symbol = "XRP"
            ),
            Coin(
                id = "solanaId",
                isActive = false,
                name = "Solana",
                rank = 9,
                symbol = "SOL"
            ),
            Coin(
                id = "polcadotId",
                isActive = true,
                name = "Polkadot",
                rank = 10,
                symbol = "DOT"
            ),
            Coin(
                id = "usdCoinId",
                isActive = false,
                name = "Usd Coin",
                rank = 11,
                symbol = "USDC"
            ),
            Coin(
                id = "uniswapId",
                isActive = false,
                name = "Uniswap",
                rank = 12,
                symbol = "UNI"
            ),
            Coin(
                id = "test1Id",
                isActive = false,
                name = "Test1",
                rank = 13,
                symbol = "TST1"
            ),
            Coin(
                id = "test2Id",
                isActive = false,
                name = "Test2",
                rank = 14,
                symbol = "TST2"
            ),
        ))
    }

    override suspend fun fetchCoinById(coinId: String): Result<Coin, DataError.Network> {
        TODO("Not yet implemented")
    }

}