package com.foivos.mycryptoapp.data.di

import com.foivos.mycryptoapp.data.local.CoinDao
import com.foivos.mycryptoapp.data.local.CoinDatabase
import com.foivos.mycryptoapp.data.local.RoomLocalCoinDataSource
import com.foivos.mycryptoapp.data.remote.CoinPaprikaApi
import com.foivos.mycryptoapp.data.remote.RetrofitRemoteCoinDataSource
import com.foivos.mycryptoapp.data.repository.CoinRepositoryImpl
import com.foivos.mycryptoapp.domain.data_source.LocalCoinDataSource
import com.foivos.mycryptoapp.domain.data_source.RemoteCoinDataSource
import com.foivos.mycryptoapp.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class CoinRepositoryModule {

    @Provides
    fun provideRemoteCoinDataSource(
        api: CoinPaprikaApi
    ): RemoteCoinDataSource {
        return RetrofitRemoteCoinDataSource(api)
    }

    @Provides
    fun provideCoinDao(
        coinDatabase: CoinDatabase
    ): CoinDao {
        return coinDatabase.dao
    }

    @Provides
    fun provideLocalCoinDataSource(
        coinDao: CoinDao
    ): LocalCoinDataSource {
        return RoomLocalCoinDataSource(coinDao)
    }

    @Provides
    fun provideCoinRepository(
        localCoinDataSource: LocalCoinDataSource,
        remoteCoinDataSource: RemoteCoinDataSource
    ): CoinRepository {
        return CoinRepositoryImpl(
            localCoinDataSource,
            remoteCoinDataSource
        )
    }

}