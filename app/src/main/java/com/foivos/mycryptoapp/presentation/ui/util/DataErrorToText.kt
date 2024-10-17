package com.foivos.mycryptoapp.presentation.ui.util

import com.foivos.mycryptoapp.R
import com.foivos.mycryptoapp.domain.util.DataError

fun DataError.asUiText(): UiText {
    return when (this) {
        DataError.Network.NO_INTERNET -> {
            UiText.StringResource(R.string.error_no_internet)
        }
        DataError.Network.SERIALIZATION -> {
            UiText.StringResource(R.string.error_serialization)
        }
        DataError.Network.REQUEST_TIMEOUT -> {
            UiText.StringResource(R.string.error_request_timeout)
        }
        DataError.Network.TOO_MANY_REQUESTS -> {
            UiText.StringResource(R.string.error_too_many_requests)
        }
        DataError.Network.SERVER_ERROR -> {
            UiText.StringResource(R.string.error_server)
        }
        DataError.Network.UNKNOWN -> {
            UiText.StringResource(R.string.error_unknown)
        }
        DataError.Local.STORAGE_FULL -> {
            UiText.StringResource(R.string.error_local_storage_full)
        }
    }
}