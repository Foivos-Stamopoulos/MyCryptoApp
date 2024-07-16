package com.foivos.mycryptoapp.presentation.coin_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foivos.mycryptoapp.data.remote.CoinPaprikaApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    api: CoinPaprikaApi
) : ViewModel() {

    init {
        /*viewModelScope.launch {
            try {
                val result = api.fetchCoins()
                Timber.d("result success: ${result.size}")
            } catch (exception: Exception) {
                Timber.d("result error: ${exception.message}")
            }
        }*/
    }
}