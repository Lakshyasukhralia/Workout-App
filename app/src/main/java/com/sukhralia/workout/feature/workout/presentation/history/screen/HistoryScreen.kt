package com.sukhralia.workout.feature.workout.presentation.history.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.sukhralia.workout.feature.workout.presentation.exercisedetail.screen.ExerciseDetailScreen
import com.sukhralia.workout.feature.workout.presentation.exerciselisting.component.ExerciseItem
import com.sukhralia.workout.feature.workout.presentation.history.component.HistoryCard
import com.sukhralia.workout.navigation.BaseScreen

data class HistoryScreen(var isRefresh: Boolean = false) : BaseScreen() {

    @Preview
    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow
        val screenModel = rememberScreenModel { HistoryScreenModel() }
        val uiState by screenModel.uiState.collectAsState()

        LaunchedEffect(Unit) {
            screenModel.getExerciseHistory()
        }

        Column(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(8.dp)
        ) {
            Text(
                text = "My",
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 26.sp),
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = stringResource(R.string.workout_history_title),
                style = MaterialTheme.typography.titleLarge.copy(fontSize = 32.sp),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(18.dp))
            LazyColumn(Modifier.fillMaxSize()) {
                items(uiState.exercises) { exercise ->
                    HistoryCard(exercise) { }
                }
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
            } else if (uiState.exercises.isEmpty())
                Text(
                    text = stringResource(id = R.string.no_history),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 32.sp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center)
                )
        }
    }

}