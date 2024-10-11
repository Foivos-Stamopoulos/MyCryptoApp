package com.foivos.mycryptoapp.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CoinEntity(

    @PrimaryKey(autoGenerate = false)
    val id: String,

    @ColumnInfo(name = "is_active")
    val isActive: Boolean,

    val name: String,

    val rank: Int,

    val symbol: String

)