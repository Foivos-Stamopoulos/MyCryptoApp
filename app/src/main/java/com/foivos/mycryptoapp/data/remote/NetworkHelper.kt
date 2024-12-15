package com.foivos.mycryptoapp.data.remote

import com.foivos.mycryptoapp.domain.util.DataError
import com.foivos.mycryptoapp.domain.util.Result
import retrofit2.HttpException
import java.nio.channels.UnresolvedAddressException
import kotlin.coroutines.cancellation.CancellationException

object NetworkHelper {

    suspend fun <T> safeApiCall(apiCall: suspend () -> T): Result<T, DataError.Network> {
        return try {
            val response = apiCall()
            Result.Success(response)
        } catch (exception: Throwable) {
            exceptionToErrorResult(exception)
        }
    }

    private fun exceptionToErrorResult(e: Throwable): Result<Nothing, DataError.Network> {
        return when (e) {
            is UnresolvedAddressException -> {
                e.printStackTrace()
                Result.Error(DataError.Network.NO_INTERNET)
            }
            is IllegalArgumentException -> {
                e.printStackTrace()
                return Result.Error(DataError.Network.SERIALIZATION)
            }
            is HttpException -> {
                httpExceptionToErrorResult(e)
            }
            else -> {
                if (e is CancellationException) throw e
                e.printStackTrace()
                return Result.Error(DataError.Network.UNKNOWN)
            }
        }
    }

    private fun httpExceptionToErrorResult(
        e: HttpException,
    ): Result<Nothing, DataError.Network> {
        val statusCode = e.code()
        return when(statusCode) {
            408 -> Result.Error(DataError.Network.REQUEST_TIMEOUT)
            429 -> Result.Error(DataError.Network.TOO_MANY_REQUESTS)
            in 500..599 -> Result.Error(DataError.Network.SERVER_ERROR)
            else -> Result.Error(DataError.Network.UNKNOWN)
        }
    }

}