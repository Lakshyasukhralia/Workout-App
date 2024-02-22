package com.sukhralia.workout.feature.workout.presentation.history.screen

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.sukhralia.workout.feature.workout.domain.repository.WorkoutRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HistoryScreenModel() : ScreenModel, KoinComponent {

    private var _uiState = MutableStateFlow(HistoryState())
    val uiState = _uiState.asStateFlow()

    private val workoutRepository by inject<WorkoutRepository>()

    init {
//        getExerciseHistory()
    }

    fun getExerciseHistory() {
        screenModelScope.launch {
            val exercises = workoutRepository.getAllExerciseFromDb()
            _uiState.update {
                it.copy(
                    exercises = exercises,
                    error = "",
                    isLoading = false,
                )
            }
        }
    }
}