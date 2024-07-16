package com.foivos.mycryptoapp.data.remote.dto

import com.foivos.mycryptoapp.domain.model.TeamMember

data class TeamMemberDto(
    val id: String,
    val name: String,
    val position: String
)

fun TeamMemberDto.toTeamMember(): TeamMember {
    return TeamMember(
        id = id,
        name = name,
        position = position
    )
}

