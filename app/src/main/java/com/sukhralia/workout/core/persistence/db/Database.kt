package com.sukhralia.workout.core.persistence.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.sukhralia.workout.core.persistence.db.converter.Converters
import com.sukhralia.workout.feature.workout.data.local.dao.ExerciseDao
import com.sukhralia.workout.feature.workout.data.local.entity.ExerciseEntity

@Database(entities = [ExerciseEntity::class], version = 2)
@TypeConverters(Converters::class)
abstract class Database : RoomDatabase() {

    companion object {
        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE workout_history ADD COLUMN demo_video TEXT")
            }
        }

        val activeMigrations: Array<Migration> = arrayOf(MIGRATION_1_2)
    }

    abstract fun exerciseDao(): ExerciseDao

}