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


}