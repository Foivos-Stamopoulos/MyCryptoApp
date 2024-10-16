package com.foivos.mycryptoapp.data.di

import com.foivos.mycryptoapp.domain.model.Coin
import com.foivos.mycryptoapp.domain.model.CoinDetail
import com.foivos.mycryptoapp.domain.model.TeamMember
import com.foivos.mycryptoapp.domain.repository.CoinRepository
import com.foivos.mycryptoapp.domain.util.DataError
import com.foivos.mycryptoapp.domain.util.EmptyResult
import com.foivos.mycryptoapp.domain.util.Result
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Singleton

@TestInstallIn(components = [SingletonComponent::class],
    replaces = [CoinRepositoryModule::class])
@Module
object FakeCoinRepositoryModule {

    @Singleton
    @Provides
    fun provideFakeCoinRepository(): CoinRepository {
        return FakeCoinRepositoryImpl()
    }

}

class FakeCoinRepositoryImpl: CoinRepository {

    private var shouldReturnNetworkError = false

    override fun getCoins(): Flow<List<Coin>> {
        return flow {
            emit(coins)
        }
    }

    override suspend fun fetchCoins(): EmptyResult<DataError> {
        return if (shouldReturnNetworkError) {
            Result.Error(DataError.Network.UNKNOWN)
        } else {
            Result.Success(Unit)
        }
    }

    override suspend fun fetchCoinDetail(coinId: String): Result<CoinDetail, DataError.Network> {
        return if (shouldReturnNetworkError) {
            Result.Error(DataError.Network.UNKNOWN)
        } else {
            Result.Success(coinDetail)
        }
    }

    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }

}

val coins = listOf(
    Coin(
        id = "btc-bitcoin",
        isActive = true,
        name = "Bitcoin",
        rank = 0,
        symbol = "BTC",
        displayName = "Bitcoin (BTC)"
    ),
    Coin(
        id = "tether-hermionegrangerclintonamberamyrose9inu",
        isActive = false,
        name = "HermioneGrangerClintonAmberAmyRose9Inu",
        rank = 0,
        symbol = "TETHER",
        displayName = "HermioneGrangerClintonAmberAmyRose9Inu (TETHER)"
    )
)

val coinDetail = CoinDetail(
    coinId = "1",
    name = "Bitcoin",
    description = "Bitcoin is lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum",
    symbol = "BTC",
    rank = 1,
    isActive = true,
    tags = listOf("Mining", "Cryptocurrency", "Payments", "Digital currency", "Encryption", "Virtual accounting system"),
    team = listOf(
        TeamMember(
            id = "1",
            name =  "John Smith",
            position = "Founder"
        ),
        TeamMember(
            id = "1",
            name =  "Juliana Smith",
            position = "Co-Founder"
        )
    )
)