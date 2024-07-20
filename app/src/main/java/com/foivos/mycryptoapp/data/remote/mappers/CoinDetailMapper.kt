package com.foivos.mycryptoapp.data.remote.mappers

import com.foivos.mycryptoapp.data.remote.dto.CoinDetailDto
import com.foivos.mycryptoapp.domain.model.CoinDetail
import com.foivos.mycryptoapp.domain.model.TeamMember

fun CoinDetailDto.toCoinDetail(): CoinDetail {
    return CoinDetail(
        coinId = id,
        name = name,
        description = description,
        symbol = symbol,
        rank = rank,
        isActive = isActive,
        tags = tagDtos.map { it.name },
        team = team.map { it.toTeamMember() }
    )
}