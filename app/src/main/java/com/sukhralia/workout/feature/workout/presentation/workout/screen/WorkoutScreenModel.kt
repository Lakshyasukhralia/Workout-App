package com.sukhralia.workout.feature.workout.presentation.workout.screen

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

class WorkoutScreenModel : ScreenModel, KoinComponent {

    private var _uiState = MutableStateFlow(WorkoutState())
    val uiState = _uiState.asStateFlow()

    private val workoutRepository by inject<WorkoutRepository>()

    init {
        getWorkouts()
    }

    fun getWorkouts(searchQuery: String? = "") {

        screenModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            workoutRepository.getWorkouts("name~\"$searchQuery\"")
                .onSuccess { workouts ->
                    _uiState.update {
                        it.copy(
                            workouts = workouts,
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