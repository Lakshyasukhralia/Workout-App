package com.sukhralia.workout

import android.app.Application
import com.sukhralia.workout.core.di.networkModule
import com.sukhralia.workout.feature.workout.di.workoutModule
import org.koin.core.context.startKoin

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                networkModule(),
                workoutModule()
            )
        }
    }
}
