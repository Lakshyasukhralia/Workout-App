package com.sukhralia.workout.feature.workout.data.dto

import com.sukhralia.workout.feature.workout.data.api.WorkoutApi
import com.sukhralia.workout.feature.workout.domain.model.Exercise
import kotlinx.serialization.Serializable

@Serializable
data class ExerciseDto(
    val id: String? = null,
    val categoryId: String? = null,
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
    categoryId,
    instruction,
    note
)