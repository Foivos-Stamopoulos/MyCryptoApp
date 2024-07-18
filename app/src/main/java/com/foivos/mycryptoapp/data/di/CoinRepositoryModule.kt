package com.foivos.mycryptoapp.data.di

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
    fun provideCoinRepository(
        remoteCoinDataSource: RemoteCoinDataSource
    ): CoinRepository {
        return CoinRepositoryImpl(remoteCoinDataSource)
    }

}