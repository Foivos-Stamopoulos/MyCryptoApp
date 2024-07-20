package com.foivos.mycryptoapp.data.di

import android.app.Application
import androidx.room.Room
import com.foivos.mycryptoapp.data.local.CoinDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideRoomLocalCoinDataSource(app: Application): CoinDatabase {
        return Room.databaseBuilder(
            app,
            CoinDatabase::class.java,
            "coindb.db"
        ).build()
    }

}