package com.sukhralia.workout.feature.workout.presentation.exercisedetail.screen

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.sukhralia.workout.feature.workout.domain.model.Exercise
import com.sukhralia.workout.feature.workout.domain.repository.WorkoutRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ExerciseDetailScreenModel() : ScreenModel, KoinComponent {

    private var _uiState = MutableStateFlow(ExerciseDetailState())
    val uiState = _uiState.asStateFlow()

    private val workoutRepository by inject<WorkoutRepository>()

    fun saveExercise(exercise: Exercise) {
        screenModelScope.launch {
            workoutRepository.saveExerciseToDb(exercise)
        }
    }
}