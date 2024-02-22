package com.sukhralia.workout.feature.workout.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ExerciseResponseDto(val items: List<ExerciseDto>)