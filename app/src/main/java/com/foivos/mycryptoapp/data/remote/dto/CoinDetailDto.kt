package com.foivos.mycryptoapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CoinDetailDto(
    val id: String,
    val name: String,
    val symbol: String,
    val rank: Int,
    @SerializedName("is_new")
    val isNew: Boolean,
    @SerializedName("is_active")
    val isActive: Boolean,
    val type: String,
    val tags: List<TagDto>,
    val team: List<TeamMemberDto>,
    val description: String,
    val message: String?,
    @SerializedName("open_source")
    val openSource: Boolean,
    @SerializedName("started_at")
    val startedAt: String?,
    @SerializedName("development_status")
    val developmentStatus: String?,
    @SerializedName("hardware_wallet")
    val hardwareWallet: Boolean,
    @SerializedName("proof_type")
    val proofType: String?,
    @SerializedName("org_structure")
    val orgStructure: String?,
    @SerializedName("hash_algorithm")
    val hashAlgorithm: String?,
    val links: LinksDto,
    @SerializedName("links_extended")
    val linksExtended: List<LinksExtendedDto>,
    val whitePaperDto: WhitePaperDto,
    @SerializedName("first_data_at")
    val firstDataAt: String?,
    @SerializedName("last_data_at")
    val lastDataAt: String?
)