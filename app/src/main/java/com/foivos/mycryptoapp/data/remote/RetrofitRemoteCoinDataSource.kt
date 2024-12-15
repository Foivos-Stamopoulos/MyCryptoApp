package com.foivos.mycryptoapp.data.remote

import com.foivos.mycryptoapp.data.remote.NetworkHelper.safeApiCall
import com.foivos.mycryptoapp.data.remote.dto.CoinDetailDto
import com.foivos.mycryptoapp.data.remote.dto.CoinDto
import com.foivos.mycryptoapp.data.remote.dto.LinksDto
import com.foivos.mycryptoapp.data.remote.dto.TagDto
import com.foivos.mycryptoapp.data.remote.dto.TeamMemberDto
import com.foivos.mycryptoapp.data.remote.dto.WhitePaperDto
import com.foivos.mycryptoapp.domain.data_source.RemoteCoinDataSource
import com.foivos.mycryptoapp.domain.util.DataError
import com.foivos.mycryptoapp.domain.util.Result
import javax.inject.Inject

class RetrofitRemoteCoinDataSource @Inject constructor(
    private val api: CoinPaprikaApi
) : RemoteCoinDataSource {

    override suspend fun fetchCoins(): Result<List<CoinDto>, DataError.Network> {
        return safeApiCall { api.fetchCoins() }
        //return Result.Success(mockCoinsDto)
    }

    override suspend fun fetchCoinById(coinId: String): Result<CoinDetailDto, DataError.Network> {
        return safeApiCall { api.fetchCoinById(coinId) }
        //return Result.Success(mockCoinDetailDto)
    }

    private val mockCoinsDto = listOf(
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

    private val mockCoinDetailDto = CoinDetailDto(
        id = "1",
        name = "Bitcoin",
        description = "Bitcoin is lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum",
        symbol = "BTC",
        rank = 1,
        isActive = true,
        isNew = false,
        type = "Cryptocurrency",
        message = null,
        openSource = true,
        startedAt = null,
        developmentStatus = null,
        hardwareWallet = true,
        proofType = null,
        orgStructure = null,
        hashAlgorithm = null,
        links = LinksDto(emptyList(), emptyList(), emptyList(), emptyList(), emptyList(), emptyList()),
        linksExtended = emptyList(),
        whitePaperDto = WhitePaperDto(null, null),
        firstDataAt = null,
        lastDataAt = null,
        tags = listOf(TagDto(0,0,"AS12D","tag1")),
        team = listOf(
            TeamMemberDto(
                id = "1",
                name =  "John Smith",
                position = "Founder"
            ),
            TeamMemberDto(
                id = "1",
                name =  "Juliana Smith",
                position = "Co-Founder"
            ),
            TeamMemberDto(
                id = "2",
                name =  "Robert Martin",
                position = "Co-Founder"
            ),
            TeamMemberDto(
                id = "3",
                name =  "George Hilt",
                position = "Blockchain Developer"
            ),
            TeamMemberDto(
                id = "4",
                name =  "Mark Brown",
                position = "Blockchain Developer"
            ),
            TeamMemberDto(
                id = "5",
                name =  "Sara Garcia",
                position = "Blockchain Developer"
            )
        )
    )

}