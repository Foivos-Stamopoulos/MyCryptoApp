package com.foivos.mycryptoapp.data.remote

import com.foivos.mycryptoapp.data.remote.dto.CoinDto
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
            //return Result.Success(result.subList(0, 20).map { it.toCoin() })
            return Result.Success(result.map { it.toCoin() })
        } catch(e: Exception) {
            NetworkHelper.exceptionToErrorResult(e)
        }
        //return Result.Success(mockCoinList.map { it.toCoin() })
    }

    override suspend fun fetchCoinById(coinId: String): Result<CoinDetail, DataError.Network> {
        return try {
            Result.Success(api.fetchCoinById(coinId).toCoinDetail())
        } catch (e: Exception) {
            NetworkHelper.exceptionToErrorResult(e)
        }
        //return Result.Success(mockCoinDetail)
    }

    private val mockCoinList = listOf(
        CoinDto(
            id = "btc-bitcoin",
            isActive = true,
            name = "Bitcoin",
            rank = 0,
            symbol = "BTC",
            isNew = false,
            type = ""
        ),
        CoinDto(
            id = "tether-hermionegrangerclintonamberamyrose9inu",
            isActive = false,
            name = "HermioneGrangerClintonAmberAmyRose9Inu",
            rank = 0,
            symbol = "TETHER",
            isNew = false,
            type = ""
        ),
        CoinDto(
            id = "ethereum-voldemorttrumprobotnik69pepe",
            isActive = true,
            name = "VoldemortTrumpRobotnik69Pepe",
            rank = 0,
            symbol = "ETHEREUM",
            isNew = false,
            type = ""
        ),
        CoinDto(
            id = "hexdc-hexdc",
            isActive = true,
            name = "HEXDC",
            rank = 0,
            symbol = "HEXDC",
            isNew = false,
            type = ""
        ),
        CoinDto(
            id = "bcartel-binance-cartel",
            isActive = false,
            name = "Binance Cartel",
            rank = 0,
            symbol = "BCARTEL",
            isNew = false,
            type = ""
        ),
        CoinDto(
            id = "axpr-axpire",
            isActive = true,
            name = "aXpire",
            rank = 0,
            symbol = "AXPR",
            isNew = false,
            type = ""
        ),
        CoinDto(
            id = "solanaId",
            isActive = false,
            name = "Solana",
            rank = 0,
            symbol = "SOL",
            isNew = false,
            type = ""
        ),
        CoinDto(
            id = "polcadotId",
            isActive = true,
            name = "Polkadot",
            rank = 0,
            symbol = "DOT",
            isNew = false,
            type = ""
        ),
        CoinDto(
            id = "usdCoinId",
            isActive = false,
            name = "Usd Coin",
            rank = 0,
            symbol = "USDC",
            isNew = false,
            type = ""
        ),
        CoinDto(
            id = "uniswapId",
            isActive = false,
            name = "Uniswap",
            rank = 0,
            symbol = "UNI",
            isNew = false,
            type = ""
        ),
        CoinDto(
            id = "dinu-dogey-inu",
            isActive = false,
            name = "Dogey-Inu",
            rank = 0,
            symbol = "DINU",
            isNew = false,
            type = ""
        ),
        CoinDto(
            id = "alice-alice",
            isActive = false,
            name = "Alice",
            rank = 0,
            symbol = "ALICE",
            isNew = false,
            type = ""
        ),
        CoinDto(
            id = "quadro-quadrotoken",
            isActive = false,
            name = "QUADROTOKEN",
            rank = 0,
            symbol = "QUADRO",
            isNew = false,
            type = ""
        ),
        CoinDto(
            id = "crypt-cryptonaught",
            isActive = false,
            name = "CRYPTONAUGHT",
            rank = 0,
            symbol = "CRYPT",
            isNew = false,
            type = ""
        ),
        CoinDto(
            id = "dseth-diversified-staked-eth-index-dseth",
            isActive = false,
            name = "Diversified Staked ETH Index (dsETH)",
            rank = 0,
            symbol = "DSETH",
            isNew = false,
            type = ""
        ),
        CoinDto(
            id = "tgol-tgonline-token",
            isActive = false,
            name = "TGOnline Token",
            rank = 0,
            symbol = "TGOL",
            isNew = false,
            type = ""
        ),
        CoinDto(
            id = "nepe-nyanpepe",
            isActive = false,
            name = "NyanPepe",
            rank = 0,
            symbol = "NEPE",
            isNew = false,
            type = ""
        ),
        CoinDto(
            id = "gucci-guccicake",
            isActive = false,
            name = "GucciCake",
            rank = 0,
            symbol = "GUCCI",
            isNew = false,
            type = ""
        ),
        CoinDto(
            id = "popo-popo-the-genie",
            isActive = false,
            name = "POPO The GENIE",
            rank = 0,
            symbol = "POPO",
            isNew = false,
            type = ""
        ),
        CoinDto(
            id = "chow-chow-chow",
            isActive = false,
            name = "CHOW CHOW",
            rank = 0,
            symbol = "CHOW",
            isNew = false,
            type = ""
        )
    )

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
                id = "1",
                name =  "Juliana Smith",
                position = "Co-Founder"
            ),
            TeamMember(
                id = "2",
                name =  "Robert Martin",
                position = "Co-Founder"
            ),
            TeamMember(
                id = "3",
                name =  "George Hilt",
                position = "Blockchain Developer"
            ),
            TeamMember(
                id = "4",
                name =  "Mark Brown",
                position = "Blockchain Developer"
            ),
            TeamMember(
                id = "5",
                name =  "Sara Garcia",
                position = "Blockchain Developer"
            )
        )
    )

}