package com.foivos.mycryptoapp.domain.util.preview_data

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.foivos.mycryptoapp.domain.model.CoinDetail
import com.foivos.mycryptoapp.domain.model.TeamMember

class CoinDetailProvider : PreviewParameterProvider<CoinDetail> {

    override val values: Sequence<CoinDetail>
        get() = sequenceOf(
            CoinDetail(
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
        )

}