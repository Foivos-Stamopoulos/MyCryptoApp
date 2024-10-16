package com.foivos.mycryptoapp.domain.util.preview_data

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.foivos.mycryptoapp.data.remote.dto.CoinDto
import com.foivos.mycryptoapp.data.remote.mappers.toCoin
import com.foivos.mycryptoapp.domain.model.Coin

class CoinsProvider : PreviewParameterProvider<List<Coin>> {

    override val values: Sequence<List<Coin>>
        get() = sequenceOf(
            listOf(
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
                )
            ).map { it.toCoin() }
        )

}