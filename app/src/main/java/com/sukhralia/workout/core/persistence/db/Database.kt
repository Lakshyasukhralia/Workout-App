package com.sukhralia.workout.core.persistence.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sukhralia.workout.core.persistence.db.converter.Converters
import com.sukhralia.workout.feature.workout.data.local.dao.ExerciseDao
import com.sukhralia.workout.feature.workout.data.local.entity.ExerciseEntity

@Database(entities = [ExerciseEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class Database : RoomDatabase() {
    abstract fun exerciseDao(): ExerciseDao

}