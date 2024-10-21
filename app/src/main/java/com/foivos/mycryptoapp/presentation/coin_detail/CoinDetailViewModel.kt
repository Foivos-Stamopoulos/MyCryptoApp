package com.foivos.mycryptoapp.presentation.coin_detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foivos.mycryptoapp.Constants
import com.foivos.mycryptoapp.domain.repository.CoinRepository
import com.foivos.mycryptoapp.domain.util.Result
import com.foivos.mycryptoapp.presentation.util.asUiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val coinRepository: CoinRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    var state by mutableStateOf(CoinDetailState())
        private set

    private var eventChannel = Channel<CoinDetailEvent>()
        val events = eventChannel.receiveAsFlow()

    private val coinId = savedStateHandle.get<String>(Constants.PARAM_COIN_ID)

    init {

        coinId?.let { coinId ->
            fetchCoinDetail(coinId)
        }

    }

    private fun fetchCoinDetail(coinId: String) {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            when (val result = coinRepository.fetchCoinDetail(coinId)) {
                is Result.Error -> {
                    state = state.copy(isLoading = false)
                    eventChannel.send(CoinDetailEvent.Error(result.error.asUiText()))
                }
                is Result.Success -> {
                    state = state.copy(isLoading = false, coinDetail = result.data)
                }
            }
        }
    }

}