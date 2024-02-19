package com.sukhralia.workout.feature.workout.di

import com.sukhralia.workout.feature.workout.data.api.WorkoutApi
import com.sukhralia.workout.feature.workout.data.repository.WorkoutRepositoryImpl
import com.sukhralia.workout.feature.workout.domain.repository.WorkoutRepository
import org.koin.core.module.Module
import org.koin.dsl.module

val workoutModule: () -> Module
    get() = {
        module {
            single { WorkoutApi(httpClient = get()) }
            single<WorkoutRepository> { WorkoutRepositoryImpl() }
        }
    }