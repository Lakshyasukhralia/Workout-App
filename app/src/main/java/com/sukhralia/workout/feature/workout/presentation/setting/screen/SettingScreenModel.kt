package com.sukhralia.workout.feature.workout.presentation.setting.screen

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.sukhralia.workout.core.persistence.preference.PreferenceKeys
import com.sukhralia.workout.core.persistence.preference.PreferenceRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SettingScreenModel() : ScreenModel, KoinComponent {


    private val preferenceRepository by inject<PreferenceRepository>()

    private var _uiState = MutableStateFlow(SettingState())
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.value.isDarkMode =
            runBlocking { preferenceRepository.getBoolean(PreferenceKeys.IS_DARK_MODE, true) }
    }

    fun saveDarkModePreference(isDarkMode: Boolean) {
        screenModelScope.launch {
            preferenceRepository.putBoolean(PreferenceKeys.IS_DARK_MODE, isDarkMode)
        }
    }

}