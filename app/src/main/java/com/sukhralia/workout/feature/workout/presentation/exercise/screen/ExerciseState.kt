package com.sukhralia.workout.feature.workout.presentation.exercise.screen

import com.sukhralia.workout.feature.workout.domain.model.Exercise

data class ExerciseState(
    val isLoading: Boolean = false,
    val error: String = "",
    val exercises: List<Exercise> = emptyList()
)
