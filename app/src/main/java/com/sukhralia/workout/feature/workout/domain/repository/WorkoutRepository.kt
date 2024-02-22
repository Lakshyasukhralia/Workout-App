package com.sukhralia.workout.feature.workout.domain.repository

import com.sukhralia.workout.feature.workout.domain.model.Exercise
import com.sukhralia.workout.feature.workout.domain.model.Workout

interface WorkoutRepository {
    suspend fun getWorkouts(filter: String?): Result<List<Workout>>
    suspend fun getExercises(filter: String?): Result<List<Exercise>>
    suspend fun saveExerciseToDb(exercise: Exercise)
    suspend fun getAllExerciseFromDb(): List<Exercise>
}