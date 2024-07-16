package com.foivos.mycryptoapp.presentation.coin_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(

) : ViewModel() {

    var state by mutableStateOf(CoinListState())
        private set

    private var eventChannel = Channel<CoinListEvent>()
    val events = eventChannel.receiveAsFlow()


}