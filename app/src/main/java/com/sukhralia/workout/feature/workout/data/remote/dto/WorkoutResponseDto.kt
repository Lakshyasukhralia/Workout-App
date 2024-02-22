package com.sukhralia.workout.feature.workout.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class WorkoutResponseDto(val items: List<WorkoutDto>)