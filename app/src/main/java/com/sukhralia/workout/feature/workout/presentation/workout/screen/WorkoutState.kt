package com.sukhralia.workout.feature.workout.presentation.workout.screen

import com.sukhralia.workout.feature.workout.domain.model.Workout

data class WorkoutState(
    val isLoading: Boolean = false,
    val error: String = "",
    val workouts: List<Workout> = emptyList(),
    var searchQuery: String = ""
)
