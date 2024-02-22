package com.sukhralia.workout.feature.workout.data.remote.dto

import com.sukhralia.workout.feature.workout.data.remote.api.WorkoutApi
import com.sukhralia.workout.feature.workout.domain.model.Exercise
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExerciseDto(
    val id: String? = null,
    @SerialName("workout_id") val workoutId: String? = null,
    val name: String? = null,
    val description: String? = null,
    val images: List<String>? = null,
    val collectionId: String? = null,
    val instruction: String? = null,
    val note: String? = null,
)

fun ExerciseDto.toExercise() = Exercise(
    id,
    name,
    description,
    images?.map { "${WorkoutApi.photoPath}/api/files/$collectionId/$id/$it" },
    workoutId,
    instruction,
    note,
    "${WorkoutApi.photoPath}/api/files/$collectionId/$id/${images?.getOrNull(0)}"
)