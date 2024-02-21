package com.sukhralia.workout.feature.workout.presentation.setting.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.sukhralia.workout.R
import com.sukhralia.workout.feature.workout.presentation.setting.model.LocalTheme
import com.sukhralia.workout.navigation.BaseScreen
import org.koin.core.component.KoinComponent

class SettingScreen : BaseScreen(), KoinComponent {


    @Preview
    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow
        val screenModel = rememberScreenModel { SettingScreenModel() }
        val uiState by screenModel.uiState.collectAsState()

        var darkMode by remember { mutableStateOf(uiState.isDarkMode) }
        LocalTheme.current.isDark.value = darkMode

        Column(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(8.dp)
        ) {
            Text(
                text = stringResource(R.string.setting),
                style = MaterialTheme.typography.titleLarge.copy(fontSize = 32.sp),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(18.dp))

            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = stringResource(R.string.dark_mode),
                    style = MaterialTheme.typography.titleMedium.copy(fontSize = 22.sp),
                )
                Spacer(modifier = Modifier.weight(1f))
                Switch(
                    colors = SwitchDefaults.colors(
                        checkedBorderColor = MaterialTheme.colorScheme.onPrimary,
                        uncheckedBorderColor = MaterialTheme.colorScheme.onPrimary,
                        checkedTrackColor = MaterialTheme.colorScheme.inversePrimary,
                        uncheckedTrackColor = MaterialTheme.colorScheme.inversePrimary
                    ),
                    checked = uiState.isDarkMode,
                    onCheckedChange = {
                        darkMode = it
                        uiState.isDarkMode = darkMode
                        screenModel.saveDarkModePreference(darkMode)
                    }
                )
            }
        }

        Box(Modifier.fillMaxSize()) {
            if (uiState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(75.dp),
                    color = MaterialTheme.colorScheme.onPrimary,
                )
            } else if (uiState.error.isNotEmpty())
                Text(
                    text = stringResource(id = R.string.error),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 32.sp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center)
                )
        }
    }
}