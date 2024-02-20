package com.sukhralia.workout.feature.workout.presentation.exerciselisting.screen

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.sukhralia.workout.core.network.errors.NetworkException
import com.sukhralia.workout.feature.workout.domain.model.Workout
import com.sukhralia.workout.feature.workout.domain.repository.WorkoutRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ExerciseListingScreenModel(val workout: Workout = Workout()) : ScreenModel, KoinComponent {

    private var _uiState = MutableStateFlow(ExerciseListingState())
    val uiState = _uiState.asStateFlow()

    private val workoutRepository by inject<WorkoutRepository>()

    init {
        if (workout.name == "All") workout.id = null
        getExercises(workout.id)
    }

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