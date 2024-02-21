package com.sukhralia.workout.navigation

import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import java.util.UUID

abstract class BaseScreen : Screen {
    override val key: ScreenKey = UUID.randomUUID().toString()
}