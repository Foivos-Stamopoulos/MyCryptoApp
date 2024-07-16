package com.foivos.mycryptoapp.domain.util.preview_data

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.foivos.mycryptoapp.domain.model.Coin

class CoinsProvider : PreviewParameterProvider<List<Coin>> {

    override val values: Sequence<List<Coin>>
        get() = sequenceOf(
            listOf(
                Coin(
                    id = "bitcoinId",
                    isActive = true,
                    name = "Bitcoin",
                    rank = 1,
                    symbol = "BTC"
                ),
                Coin(
                    id = "tetherId",
                    isActive = false,
                    name = "Tether",
                    rank = 2,
                    symbol = "USDT"
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
                )
            )
        )

}