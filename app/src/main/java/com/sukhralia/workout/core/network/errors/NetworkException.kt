package com.sukhralia.workout.core.network.errors

enum class NetworkError {
    ServiceUnavailable,
    ClientError,
    ServerError,
    UnknownError
}

class NetworkException(val error: NetworkError, val statusCode: Int = -1) : Exception(
    "Something went wrong: $error"
)