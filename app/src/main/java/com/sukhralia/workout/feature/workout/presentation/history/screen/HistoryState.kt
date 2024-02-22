package com.sukhralia.workout.feature.workout.presentation.history.screen

import com.sukhralia.workout.feature.workout.domain.model.Exercise

data class HistoryState(
    val isLoading: Boolean = false,
    val error: String = "",
    val exercises: List<Exercise> = emptyList(),
    var searchQuery: String = ""
)
