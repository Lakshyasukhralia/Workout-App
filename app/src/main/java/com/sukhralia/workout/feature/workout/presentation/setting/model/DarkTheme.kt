package com.sukhralia.workout.feature.workout.presentation.setting.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateOf

data class DarkTheme(var isDark: MutableState<Boolean> = mutableStateOf(true))

val LocalTheme = compositionLocalOf { DarkTheme() }