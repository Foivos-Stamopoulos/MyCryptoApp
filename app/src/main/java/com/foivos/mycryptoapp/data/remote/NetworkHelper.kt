package com.foivos.mycryptoapp.data.remote

import com.foivos.mycryptoapp.domain.util.DataError
import retrofit2.Response

object NetworkHelper {

    /*suspend inline fun <reified T> responseToResult(response: okhttp3.Response): Result<T, DataError.Network> {
        return when(response.status.value) {
            in 200..299 -> Result.Success(response.body<T>())
            401 -> Result.Error(DataError.Network.UNAUTHORIZED)
            408 -> Result.Error(DataError.Network.REQUEST_TIMEOUT)
            409 -> Result.Error(DataError.Network.CONFLICT)
            413 -> Result.Error(DataError.Network.PAYLOAD_TOO_LARGE)
            429 -> Result.Error(DataError.Network.TOO_MANY_REQUESTS)
            in 500..599 -> Result.Error(DataError.Network.SERVER_ERROR)
            else -> Result.Error(DataError.Network.UNKNOWN)
        }
    }*/

}