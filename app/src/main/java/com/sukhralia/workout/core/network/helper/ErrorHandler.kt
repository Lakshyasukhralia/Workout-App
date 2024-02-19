package com.sukhralia.workout.core.network.helper

import com.sukhralia.workout.core.network.errors.NetworkError
import com.sukhralia.workout.core.network.errors.NetworkException
import com.sukhralia.workout.core.network.networkDispatchers
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.withContext

suspend inline fun <reified T> handleErrors(
    crossinline response: suspend () -> HttpResponse
): T = withContext(networkDispatchers.io) {

    val result = try {
        response()
    } catch (e: IOException) {
        throw NetworkException(NetworkError.ServiceUnavailable, -1)
    }

    val statusCode = result.status.value

    when (statusCode) {
        in 200..299 -> Unit
        in 400..499 -> throw NetworkException(NetworkError.ClientError, statusCode)
        500 -> throw NetworkException(NetworkError.ServerError, statusCode)
        else -> throw NetworkException(NetworkError.UnknownError, statusCode)
    }

    return@withContext try {
        result.body()
    } catch (e: Exception) {
        print(e.toString())
        throw NetworkException(NetworkError.ServerError, statusCode)
    }

}