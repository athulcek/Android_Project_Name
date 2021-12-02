package com.ouvrirdeveloper.domain.models.common
/*

data class Resource<out T>(
    val status: Status,
    val data: T?,
    val message: String?,
    val errorCode: ErrorStatus = ErrorStatus.None
) {
    fun isSuccess(): Boolean = status == Status.SUCCESS

    companion object {

        fun <T> initial(data: T?): Resource<T> {
            return Resource(Status.INITIAL, data, null)
        }

        fun <T> empty(data: T?): Resource<T> {
            return Resource(Status.EMPTY, data, null)
        }

        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }

        fun <T> networkError(msg: String, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, msg,ErrorStatus.NETWORK_ERROR)
        }

        fun <T> httpError(msg: String, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, msg, ErrorStatus.HTTP_ERROR)
        }

        fun <T> genericError(msg: String, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, msg,ErrorStatus.GENERIC_ERROR)
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
    }
}

sealed class ErrorStatus {
    object None : ErrorStatus()
    object NETWORK_ERROR : ErrorStatus()
    object HTTP_ERROR : ErrorStatus()
    object GENERIC_ERROR : ErrorStatus()
}*/

sealed class Resource<out T>(
    val data: T? = null,
    val message: String? = null,
    val erroStatus: ErrorStatus? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null, erroStatus: ErrorStatus?) :
        Resource<T>(data, message, erroStatus)

    class Loading<T> : Resource<T>()

    /* fun <T> error(msg: String, data: T?, erroCode: ErrorCode?): Resource<T> {
         return Resource.Error(msg, data, erroCode)
     }

     fun <T> success(data: T): Resource<T> {
         return Resource.Success(data)
     }

     fun <T> loading(data: T? = null): Resource<T> {
         return Resource.Loading()
     }*/
}

open class ErrorStatus
object Null : ErrorStatus()
object NullorEmpty : ErrorStatus()
object NETWORK_ERROR : ErrorStatus()
data class HTTP_ERROR(val errorCode:Int) : ErrorStatus()
object GENERIC_ERROR : ErrorStatus()
object IOException : ErrorStatus()
object Unknown : ErrorStatus()
