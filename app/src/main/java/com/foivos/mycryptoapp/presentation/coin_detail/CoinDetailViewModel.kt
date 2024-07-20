package com.foivos.mycryptoapp.presentation.coin_detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foivos.mycryptoapp.domain.model.CoinDetail
import com.foivos.mycryptoapp.domain.model.TeamMember
import com.foivos.mycryptoapp.domain.repository.CoinRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    coinRepository: CoinRepository
): ViewModel() {

    var state by mutableStateOf(CoinDetailState())
        private set

    private var eventChannel = Channel<CoinDetailEvent>()
        val events = eventChannel.receiveAsFlow()

    init {

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
                    id = "2",
                    name =  "George Hilt",
                    position = "Blockchain Developer"
                ),
                TeamMember(
                    id = "3",
                    name =  "Mark Brown",
                    position = "Blockchain Developer"
                ),
                TeamMember(
                    id = "4",
                    name =  "Sara Garcia",
                    position = "Blockchain Developer"
                )
            )
        )

        viewModelScope.launch {
            state = state.copy(isLoading = true)
            delay(2000)
            state = state.copy(isLoading = false, coinDetail = coinDetail)
        }
    }

}