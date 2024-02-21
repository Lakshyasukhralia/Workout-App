package com.sukhralia.workout

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.sukhralia.workout.core.persistence.preference.PreferenceKeys.IS_DARK_MODE
import com.sukhralia.workout.core.persistence.preference.PreferenceRepository
import com.sukhralia.workout.feature.workout.presentation.setting.model.DarkTheme
import com.sukhralia.workout.feature.workout.presentation.setting.model.LocalTheme
import com.sukhralia.workout.navigation.SettingTab
import com.sukhralia.workout.navigation.TabNavigationItem
import com.sukhralia.workout.navigation.WorkoutTab
import com.sukhralia.workout.ui.theme.WorkoutTheme
import kotlinx.coroutines.runBlocking
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainActivity : ComponentActivity(), KoinComponent {

    private val preferenceRepository by inject<PreferenceRepository>()

    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val isDarkMode = runBlocking { preferenceRepository.getBoolean(IS_DARK_MODE, true) }
            val darkMode = remember { mutableStateOf(isDarkMode) }

            CompositionLocalProvider(LocalTheme provides DarkTheme(darkMode)) {
                WorkoutTheme(darkTheme = LocalTheme.current.isDark.value, dynamicColor = false) {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        TabNavigator(WorkoutTab) {
                            Scaffold(
                                content = { p ->
                                    Column(
                                        Modifier.padding(
                                            bottom = p.calculateBottomPadding().minus(14.dp)
                                        )
                                    ) {
                                        CurrentTab()
                                    }
                                },
                                bottomBar = {
                                    BottomAppBar(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clip(RoundedCornerShape(22.dp, 22.dp))
                                    ) {
                                        TabNavigationItem(WorkoutTab)
                                        TabNavigationItem(SettingTab)
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
