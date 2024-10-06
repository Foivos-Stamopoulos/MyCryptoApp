package com.foivos.mycryptoapp.data.remote

import com.foivos.mycryptoapp.data.remote.mappers.toCoin
import com.foivos.mycryptoapp.data.remote.mappers.toCoinDetail
import com.foivos.mycryptoapp.domain.data_source.RemoteCoinDataSource
import com.foivos.mycryptoapp.domain.model.Coin
import com.foivos.mycryptoapp.domain.model.CoinDetail
import com.foivos.mycryptoapp.domain.model.TeamMember
import com.foivos.mycryptoapp.domain.util.DataError
import com.foivos.mycryptoapp.domain.util.Result
import javax.inject.Inject

class RetrofitRemoteCoinDataSource @Inject constructor(
    private val api: CoinPaprikaApi
) : RemoteCoinDataSource {

    override suspend fun fetchCoins(): Result<List<Coin>, DataError.Network> {
        return try {
            val result = api.fetchCoins()
            return Result.Success(result.subList(0, 20).map { it.toCoin() })
        } catch(e: Exception) {
            NetworkHelper.exceptionToErrorResult(e)
        }
        //return mockCoinList
    }

    override suspend fun fetchCoinById(coinId: String): Result<CoinDetail, DataError.Network> {
        return try {
            Result.Success(api.fetchCoinById(coinId).toCoinDetail())
        } catch (e: Exception) {
            NetworkHelper.exceptionToErrorResult(e)
        }

        //return Result.Success(mockCoinDetail)
    }

    private val mockCoinList = Result.Success(listOf(
        Coin(
            id = "bitcoinId",
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
        ),
        Coin(
            id = "ethereum-voldemorttrumprobotnik69pepe",
            isActive = true,
            name = "VoldemortTrumpRobotnik69Pepe",
            rank = 0,
            symbol = "ETHEREUM"
        ),
        Coin(
            id = "hexdc-hexdc",
            isActive = true,
            name = "HEXDC",
            rank = 0,
            symbol = "HEXDC"
        ),
        Coin(
            id = "bcartel-binance-cartel",
            isActive = false,
            name = "Binance Cartel",
            rank = 0,
            symbol = "BCARTEL"
        ),
        Coin(
            id = "axpr-axpire",
            isActive = true,
            name = "aXpire",
            rank = 0,
            symbol = "AXPR"
        ),
        Coin(
            id = "solanaId",
            isActive = false,
            name = "Solana",
            rank = 0,
            symbol = "SOL"
        ),
        Coin(
            id = "polcadotId",
            isActive = true,
            name = "Polkadot",
            rank = 0,
            symbol = "DOT"
        ),
        Coin(
            id = "usdCoinId",
            isActive = false,
            name = "Usd Coin",
            rank = 0,
            symbol = "USDC"
        ),
        Coin(
            id = "uniswapId",
            isActive = false,
            name = "Uniswap",
            rank = 0,
            symbol = "UNI"
        ),
        Coin(
            id = "dinu-dogey-inu",
            isActive = false,
            name = "Dogey-Inu",
            rank = 0,
            symbol = "DINU"
        ),
        Coin(
            id = "alice-alice",
            isActive = false,
            name = "Alice",
            rank = 0,
            symbol = "ALICE"
        ),
        Coin(
            id = "quadro-quadrotoken",
            isActive = false,
            name = "QUADROTOKEN",
            rank = 0,
            symbol = "QUADRO"
        ), Coin(
            id = "crypt-cryptonaught",
            isActive = false,
            name = "CRYPTONAUGHT",
            rank = 0,
            symbol = "CRYPT"
        ), Coin(
            id = "dseth-diversified-staked-eth-index-dseth",
            isActive = false,
            name = "Diversified Staked ETH Index (dsETH)",
            rank = 0,
            symbol = "DSETH"
        ), Coin(
            id = "tgol-tgonline-token",
            isActive = false,
            name = "TGOnline Token",
            rank = 0,
            symbol = "TGOL"
        ), Coin(
            id = "nepe-nyanpepe",
            isActive = false,
            name = "NyanPepe",
            rank = 0,
            symbol = "NEPE"
        ), Coin(
            id = "gucci-guccicake",
            isActive = false,
            name = "GucciCake",
            rank = 0,
            symbol = "GUCCI"
        ), Coin(
            id = "popo-popo-the-genie",
            isActive = false,
            name = "POPO The GENIE",
            rank = 0,
            symbol = "POPO"
        ), Coin(
            id = "chow-chow-chow",
            isActive = false,
            name = "CHOW CHOW",
            rank = 0,
            symbol = "CHOW"
        )
    ))

    private val mockCoinDetail = CoinDetail(
        coinId = "1",
        name = "Bitcoin",
        description = "Bitcoin is lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum",
        symbol = "BTC",
        rank = 1,
        isActive = true,
        tags = listOf("Mining", "Cryptocurrency", "Payments", "Digital currency", "Encryption", "Virtual accounting system"),
        team = listOf(
            TeamMember(
                id = "1",
                name =  "John Smith",
                position = "Founder"
            ),
            TeamMember(
                id = "2",
                name =  "George Hilt",
                position = "Blockchain Developer"
            ),
            TeamMember(
                id = "3",
                name =  "Mark Brown",
                position = "Blockchain Developer"
            ),
            TeamMember(
                id = "4",
                name =  "Sara Garcia",
                position = "Blockchain Developer"
            )
        )
    )

}