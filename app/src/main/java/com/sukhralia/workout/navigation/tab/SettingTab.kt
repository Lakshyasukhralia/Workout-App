package com.sukhralia.workout.navigation.tab

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import cafe.adriel.voyager.transitions.SlideTransition
import com.sukhralia.workout.R
import com.sukhralia.workout.feature.workout.presentation.setting.screen.SettingScreen

object SettingTab : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val title = stringResource(R.string.setting)
            val icon = rememberVectorPainter(Icons.Default.Settings)

            return remember {
                TabOptions(
                    index = 0u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        Navigator(SettingScreen()) { navigator ->
            SlideTransition(navigator)
        }
    }

}