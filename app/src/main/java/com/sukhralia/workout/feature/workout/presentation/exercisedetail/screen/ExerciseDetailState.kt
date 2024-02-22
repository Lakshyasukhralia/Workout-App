package com.sukhralia.workout.feature.workout.presentation.exercisedetail.screen

data class ExerciseDetailState(
    val isLoading: Boolean = false,
    val error: String = "",
    val isSaved: Boolean = false
)
