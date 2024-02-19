package com.sukhralia.workout.feature.workout.presentation.exercise.screen

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.sukhralia.workout.core.network.errors.NetworkException
import com.sukhralia.workout.feature.workout.domain.repository.WorkoutRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ExerciseScreenModel : ScreenModel, KoinComponent {

    private var _uiState = MutableStateFlow(ExerciseState())
    val uiState = _uiState.asStateFlow()

    private val workoutRepository by inject<WorkoutRepository>()

    fun getExercises(workoutId: String? = "", searchQuery: String? = "") {

        screenModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            val filter =
                if (workoutId == null) "name~\"$searchQuery\"" else "workout_id=\"$workoutId\"&&name~\"$searchQuery\""

            workoutRepository.getExercises(filter)
                .onSuccess { exercise ->
                    _uiState.update {
                        it.copy(
                            exercises = exercise,
                            error = "",
                            isLoading = false,
                        )
                    }
                }
                .onFailure { e ->

                    val err = e as? NetworkException

                    _uiState.update {
                        it.copy(
                            error = e.message.toString(),
                            isLoading = false
                        )
                    }
                }
        }
    }
}