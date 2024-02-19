package com.sukhralia.workout.feature.workout.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class WorkoutResponseDto(val items: List<WorkoutDto>)