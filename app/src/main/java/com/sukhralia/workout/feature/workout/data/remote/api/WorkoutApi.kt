package com.sukhralia.workout.feature.workout.data.remote.api

import com.sukhralia.workout.core.network.helper.handleErrors
import com.sukhralia.workout.feature.workout.data.remote.dto.ExerciseResponseDto
import com.sukhralia.workout.feature.workout.data.remote.dto.WorkoutResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.ContentType
import io.ktor.http.contentType

class WorkoutApi(
    private val httpClient: HttpClient
) {

    companion object {
        //Local
//        const val photoPath = "http://192.168.29.52:8090"
//        private const val baseUrl = "http://192.168.29.52:8090/api/collections"

        //Remote
        const val photoPath = "http://172.105.39.153:83"
        private const val baseUrl = "http://172.105.39.153:83/api/collections"

        const val workoutRoute = baseUrl.plus("/workouts/records")
        const val exerciseRoute = baseUrl.plus("/exercises/records")
    }

    suspend fun getWorkouts(filter: String?): WorkoutResponseDto {
        return handleErrors {
            httpClient.get(workoutRoute) {
                contentType(ContentType.Application.Json)
                if (!filter.isNullOrEmpty())
                    parameter("filter", filter)
            }
        }
    }

    suspend fun getExercises(filter: String?): ExerciseResponseDto {
        return handleErrors {
            httpClient.get(exerciseRoute) {
                contentType(ContentType.Application.Json)
                if (!filter.isNullOrEmpty())
                    parameter("filter", filter)
            }
        }
    }
}