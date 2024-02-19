package com.sukhralia.workout.core.di

import com.sukhralia.workout.core.network.createHttpClient
import org.koin.core.module.Module
import org.koin.dsl.module

val networkModule: () -> Module
    get() = {
        module {
            single { createHttpClient() }
        }
    }