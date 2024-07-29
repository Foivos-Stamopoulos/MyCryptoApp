package com.foivos.mycryptoapp.presentation.coin_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foivos.mycryptoapp.domain.repository.CoinRepository
import com.foivos.mycryptoapp.domain.util.Result
import com.foivos.mycryptoapp.presentation.ui.asUiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    coinRepository: CoinRepository
) : ViewModel() {

    var state by mutableStateOf(CoinListState())
        private set

    private var eventChannel = Channel<CoinListEvent>()
    val events = eventChannel.receiveAsFlow()

    init {

        viewModelScope.launch {
            when (val result = coinRepository.fetchCoins()) {
                is Result.Error -> {
                    state = state.copy(isLoading = false)
                    eventChannel.send(CoinListEvent.Error(result.error.asUiText()))
                }
                is Result.Success -> {
                    // Do nothing
                }
            }
        }

        coinRepository.getCoins().onStart {
            state = state.copy(isLoading = true)
        }.onEach { coins ->
            state = state.copy(coins = coins, isLoading = false)
        }.launchIn(viewModelScope)
    }

}