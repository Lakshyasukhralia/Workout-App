package com.sukhralia.workout.feature.workout.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.sukhralia.workout.feature.workout.data.local.entity.ExerciseEntity

@Dao
interface ExerciseDao {
    @Query("SELECT * FROM workout_history ORDER BY create_date DESC")
    fun getAll(): List<ExerciseEntity>

    @Insert
    fun insertAll(vararg exercises: ExerciseEntity)

    @Delete
    fun delete(exercise: ExerciseEntity)
}