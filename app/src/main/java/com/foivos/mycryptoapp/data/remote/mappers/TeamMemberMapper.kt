package com.foivos.mycryptoapp.data.remote.mappers

import com.foivos.mycryptoapp.data.remote.dto.TeamMemberDto
import com.foivos.mycryptoapp.domain.model.TeamMember

fun TeamMemberDto.toTeamMember(): TeamMember {
    return TeamMember(
        id = id,
        name = name,
        position = position
    )
}