package com.foivos.mycryptoapp.domain.util.preview_data

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.foivos.mycryptoapp.domain.model.Coin

class CoinProvider : PreviewParameterProvider<Coin> {

    override val values: Sequence<Coin>
        get() = sequenceOf(
            Coin(
                id = "bitcoinTestId",
                isActive = true,
                name = "Bitcoin",
                rank = 1,
                symbol = "BTC",
                displayName = "Bitcoin (BTC)"
            ),
            Coin(
                id = "tetherTestId",
                isActive = false,
                name = "Tether",
                rank = 2,
                symbol = "USDT",
                displayName = "Tether (USDT)"
            )
        )

}