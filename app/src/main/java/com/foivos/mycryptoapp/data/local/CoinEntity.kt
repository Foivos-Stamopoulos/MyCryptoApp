package com.foivos.mycryptoapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class CoinEntity(

    @PrimaryKey(autoGenerate = false)
    val id: String,

    val isActive: Boolean,

    val isNew: Boolean,

    val name: String,

    val rank: Int,

    val symbol: String,

    val type: String

)