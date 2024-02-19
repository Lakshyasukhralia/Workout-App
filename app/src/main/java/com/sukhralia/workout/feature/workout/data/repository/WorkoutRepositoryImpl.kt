package com.sukhralia.workout.feature.workout.data.repository

import com.sukhralia.workout.feature.workout.data.api.WorkoutApi
import com.sukhralia.workout.feature.workout.data.dto.toExercise
import com.sukhralia.workout.feature.workout.data.dto.toWorkout
import com.sukhralia.workout.feature.workout.domain.model.Exercise
import com.sukhralia.workout.feature.workout.domain.model.Workout
import com.sukhralia.workout.feature.workout.domain.repository.WorkoutRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class WorkoutRepositoryImpl : WorkoutRepository, KoinComponent {

    private val api by inject<WorkoutApi>()

    override suspend fun getWorkouts(filter: String?): Result<List<Workout>> {
        return try {
            val response = api.getWorkouts(filter).items.map { it.toWorkout() }
            Result.success(response)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun getExercises(filter: String?): Result<List<Exercise>> {
        return try {
            val response = api.getExercises(filter).items.map { it.toExercise() }
            Result.success(response)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }
}