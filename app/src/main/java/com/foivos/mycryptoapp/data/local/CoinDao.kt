package com.foivos.mycryptoapp.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface CoinDao {

    @Query("SELECT * FROM CoinEntity")
    fun getCoins(): Flow<List<CoinEntity>>

    @Upsert
    suspend fun upsertCoin(coin: CoinEntity)

    @Upsert
    suspend fun upsertCoins(coins: List<CoinEntity>)
}