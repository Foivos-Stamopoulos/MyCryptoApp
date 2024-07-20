package com.foivos.mycryptoapp.data.remote

import com.foivos.mycryptoapp.data.remote.dto.CoinDetailDto
import com.foivos.mycryptoapp.data.remote.mappers.toCoin
import com.foivos.mycryptoapp.domain.data_source.RemoteCoinDataSource
import com.foivos.mycryptoapp.domain.model.Coin
import com.foivos.mycryptoapp.domain.util.DataError
import com.foivos.mycryptoapp.domain.util.Result
import com.foivos.mycryptoapp.domain.util.asEmptyDataResult
import com.google.gson.Gson
import retrofit2.HttpException
import java.nio.channels.UnresolvedAddressException
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

class RetrofitRemoteCoinDataSource @Inject constructor(
    private val api: CoinPaprikaApi
) : RemoteCoinDataSource {

    override suspend fun fetchCoins(): Result<List<Coin>, DataError.Network> {
        try {
            val result = api.fetchCoins()
            return Result.Success(result.map { it.toCoin() }.subList(0,80))
        } catch(e: UnresolvedAddressException) {
            e.printStackTrace()
            return Result.Error(DataError.Network.NO_INTERNET)
        } /*catch (e: SerializationException) {
            e.printStackTrace()
            return Result.Error(DataError.Network.SERIALIZATION)
        }*/ catch (httpException: HttpException) {
            return NetworkHelper.responseToErrorResult(httpException)
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            e.printStackTrace()
            return Result.Error(DataError.Network.UNKNOWN)
        }
        /*return Result.Success(listOf(
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
        ))*/
    }

    override suspend fun fetchCoinById(coinId: String): Result<Coin, DataError.Network> {

        val jsonResponse = "{\n" +
                "  \"id\": \"btc-bitcoin\",\n" +
                "  \"name\": \"Bitcoin\",\n" +
                "  \"symbol\": \"BTC\",\n" +
                "  \"parent\": {\n" +
                "    \"id\": \"eth-ethereum\",\n" +
                "    \"name\": \"Ethereum\",\n" +
                "    \"symbol\": \"ETH\"\n" +
                "  },\n" +
                "  \"rank\": 1,\n" +
                "  \"is_new\": false,\n" +
                "  \"is_active\": true,\n" +
                "  \"type\": \"coin\",\n" +
                "  \"logo\": \"https://static.coinpaprika.com/coin/bnb-binance-coin/logo.png\",\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": \"blockchain-service\",\n" +
                "      \"name\": \"Blockchain Service\",\n" +
                "      \"coin_counter\": 160,\n" +
                "      \"ico_counter\": 80\n" +
                "    }\n" +
                "  ],\n" +
                "  \"team\": [\n" +
                "    {\n" +
                "      \"id\": \"vitalik-buterin\",\n" +
                "      \"name\": \"Vitalik Buterin\",\n" +
                "      \"position\": \"Author\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"description\": \"Bitcoin is a cryptocurrency and worldwide payment system. It is the first decentralized digital currency, as the system works without a central bank or single administrator.\",\n" +
                "  \"message\": \"string\",\n" +
                "  \"open_source\": true,\n" +
                "  \"hardware_wallet\": true,\n" +
                "  \"started_at\": \"2009-01-03T00:00:00Z\",\n" +
                "  \"development_status\": \"Working product\",\n" +
                "  \"proof_type\": \"Proof of work\",\n" +
                "  \"org_structure\": \"Decentralized\",\n" +
                "  \"hash_algorithm\": \"SHA256\",\n" +
                "  \"contract\": \"string\",\n" +
                "  \"platform\": \"string\",\n" +
                "  \"contracts\": [\n" +
                "    {\n" +
                "      \"contract\": \"string\",\n" +
                "      \"platform\": \"string\",\n" +
                "      \"type\": \"string\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"links\": {\n" +
                "    \"explorer\": [\n" +
                "      \"http://blockchain.com/explorer\",\n" +
                "      \"https://blockchair.com/bitcoin/blocks\",\n" +
                "      \"https://blockexplorer.com/\",\n" +
                "      \"https://live.blockcypher.com/btc/\"\n" +
                "    ],\n" +
                "    \"facebook\": [\n" +
                "      \"https://www.facebook.com/bitcoins/\"\n" +
                "    ],\n" +
                "    \"reddit\": [\n" +
                "      \"https://www.reddit.com/r/bitcoin\"\n" +
                "    ],\n" +
                "    \"source_code\": [\n" +
                "      \"https://github.com/bitcoin/bitcoin\"\n" +
                "    ],\n" +
                "    \"website\": [\n" +
                "      \"https://bitcoin.org/\"\n" +
                "    ],\n" +
                "    \"youtube\": [\n" +
                "      \"https://www.youtube.com/watch?v=Um63OQz3bjo\"\n" +
                "    ],\n" +
                "    \"medium\": null\n" +
                "  },\n" +
                "  \"links_extended\": [\n" +
                "    {\n" +
                "      \"url\": \"http://blockchain.com/explorer\",\n" +
                "      \"type\": \"explorer\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"url\": \"https://www.reddit.com/r/bitcoin\",\n" +
                "      \"type\": \"reddit\",\n" +
                "      \"stats\": {\n" +
                "        \"subscribers\": 1009135\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"url\": \"https://github.com/bitcoin/bitcoin\",\n" +
                "      \"type\": \"source_code\",\n" +
                "      \"stats\": {\n" +
                "        \"contributors\": 730,\n" +
                "        \"stars\": 36613\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"url\": \"https://bitcoin.org/\",\n" +
                "      \"type\": \"website\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"whitepaper\": {\n" +
                "    \"link\": \"https://static.coinpaprika.com/storage/cdn/whitepapers/215.pdf\",\n" +
                "    \"thumbnail\": \"https://static.coinpaprika.com/storage/cdn/whitepapers/217.jpg\"\n" +
                "  },\n" +
                "  \"first_data_at\": \"2018-10-03T11:48:19Z\",\n" +
                "  \"last_data_at\": \"2019-05-03T11:00:00\"\n" +
                "}"

        val dto = Gson().fromJson(jsonResponse, CoinDetailDto::class.java)

        TODO("Not yet implemented")
    }

}