package com.sukhralia.workout.feature.workout.presentation.setting.screen

data class SettingState(
    val isLoading: Boolean = false,
    val error: String = "",
    var isDarkMode: Boolean = true
)
