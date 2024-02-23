package com.sukhralia.workout.core.di

import androidx.room.Room
import com.sukhralia.workout.core.persistence.db.Database
import com.sukhralia.workout.feature.workout.data.local.dao.ExerciseDao
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.Module
import org.koin.dsl.module

val databaseModule: () -> Module
    get() = {
        module {
            single {
                Room.databaseBuilder(
                    androidApplication(),
                    Database::class.java, "fitmunk_db"
                )
                    .addMigrations(*Database.activeMigrations)
                    .build()
            }
            single<ExerciseDao> {
                val database = get<Database>()
                database.exerciseDao()
            }
        }
    }

