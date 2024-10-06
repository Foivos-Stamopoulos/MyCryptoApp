package com.foivos.mycryptoapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface CoinDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoins(
        coinEntities: List<CoinEntity>
    )

    @Query(
        """
            SELECT * 
            FROM CoinEntity
            WHERE LOWER(name) LIKE '%' || LOWER(:query) || '%' OR
                UPPER(:query) == symbol
        """
    )
    suspend fun searchCoin(query: String): List<CoinEntity>

    @Query("SELECT * FROM CoinEntity")
    fun getCoins(): Flow<List<CoinEntity>>

    @Upsert
    suspend fun upsertCoin(coin: CoinEntity)

    @Upsert
    suspend fun upsertCoins(coins: List<CoinEntity>)
}