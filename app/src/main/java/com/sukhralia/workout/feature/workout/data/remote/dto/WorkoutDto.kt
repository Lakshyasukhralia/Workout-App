package com.sukhralia.workout.feature.workout.data.remote.dto

import com.sukhralia.workout.feature.workout.data.remote.api.WorkoutApi.Companion.photoPath
import com.sukhralia.workout.feature.workout.domain.model.Workout
import kotlinx.serialization.Serializable

@Serializable
data class WorkoutDto(
    val id: String? = null,
    val name: String? = null,
    val description: String? = null,
    val image: String? = null,
    val collectionId: String? = null,
)

fun WorkoutDto.toWorkout() =
    Workout(id, name, description, "$photoPath/api/files/$collectionId/$id/$image")