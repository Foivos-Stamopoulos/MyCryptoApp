package com.foivos.mycryptoapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CoinDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoins(
        coinEntities: List<CoinEntity>
    )

    @Query(
        """
            SELECT * 
            FROM coinentity
            WHERE LOWER(name) LIKE '%' || LOWER(:query) || '%' OR
                UPPER(:query) == symbol
        """
    )
    suspend fun searchCoin(query: String): List<CoinEntity>

    @Query(
        """
            SELECT * 
            FROM coinentity
        """
    )
    suspend fun getCoins(): List<CoinEntity>

}