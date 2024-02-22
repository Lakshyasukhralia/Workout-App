package com.sukhralia.workout

import android.app.Application
import com.sukhralia.workout.core.di.databaseModule
import com.sukhralia.workout.core.di.networkModule
import com.sukhralia.workout.feature.workout.di.workoutModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BaseApplication)
            modules(
                networkModule(),
                databaseModule(),
                workoutModule()
            )
        }
    }
}
