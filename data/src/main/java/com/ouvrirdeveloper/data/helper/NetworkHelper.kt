package com.ouvrirdeveloper.data

import com.ouvrirdeveloper.core.extensions.apploge
import com.ouvrirdeveloper.data.models.responses.ErrorResponse
import com.ouvrirdeveloper.domain.models.common.HTTP_ERROR
import com.ouvrirdeveloper.domain.models.common.NETWORK_ERROR
import com.ouvrirdeveloper.domain.models.common.Resource
import com.ouvrirdeveloper.domain.models.common.Unknown
import com.squareup.moshi.Moshi
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

suspend fun <T> safeApiCall(apiCall: suspend () -> T): Resource<T> {
    return try {
        var result: Resource<T>
        val response = apiCall.invoke()
        if (response == null || response is Unit) {
            result = Resource.Success(response)
        } else {
            result = Resource.Success(response)
        }
        result
    } catch (throwable: Throwable) {
        apploge("Error Thrown error in Api Call " + throwable.message)

        when (throwable) {/*
            //3xx response
            is RedirectResponseException -> Resource.genericError(
                throwable.response.status.description,
                null
            )
            //4xx response
            is ClientRequestException -> Resource.genericError(
                throwable.response.status.description,
                null
            )
            //5xx response
            is ServerResponseException -> Resource.genericError(
                throwable.response.status.description,
                null
            )*/
            is IOException -> Resource.Error(
                throwable.message ?: "Network error",
                null,
                com.ouvrirdeveloper.domain.models.common.IOException
            )
            is SocketTimeoutException -> Resource.Error(
                throwable.message ?: "Network error",
                null,
                NETWORK_ERROR
            )
            is HttpException -> {
                val errorBody = convertErrorBody(throwable)
                Resource.Error(
                    errorBody?.title ?: "",
                    null,
                    HTTP_ERROR(errorBody?.status ?: throwable.code())
                )
            }
            else -> Resource.Error(throwable.message ?: "Unknown error occurred", null, Unknown)
        }
    }
}

private fun convertErrorBody(throwable: HttpException): ErrorResponse? {
    return try {
        throwable.response()?.errorBody()?.source()?.let {
            val moshiAdapter = Moshi.Builder().build().adapter(ErrorResponse::class.java)
            moshiAdapter.fromJson(it)
        }
    } catch (exception: Exception) {
        null
    }
}