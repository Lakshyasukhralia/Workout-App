package com.sukhralia.workout.feature.workout.presentation.exerciselisting.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.sukhralia.workout.R
import com.sukhralia.workout.feature.BaseScreen
import com.sukhralia.workout.feature.workout.domain.model.Workout
import com.sukhralia.workout.feature.workout.presentation.exerciselisting.component.ExerciseItem
import com.sukhralia.workout.feature.workout.presentation.exercisedetail.screen.ExerciseDetailScreen
import com.sukhralia.workout.feature.workout.presentation.workout.component.RoundedTextField

data class ExerciseListingScreen(val workout: Workout = Workout()) : BaseScreen() {

    @Preview
    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow
        val screenModel = rememberScreenModel { ExerciseListingScreenModel(workout) }
        val uiState by screenModel.uiState.collectAsState()

        Column(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(8.dp)
        ) {
            RoundedTextField(
                label = stringResource(id = R.string.search_exercise_label, workout.name ?: ""),
                trailingIcon = Icons.Filled.Search,
                modifier = Modifier
                    .fillMaxWidth()
                    .border(.5.dp, Color.Gray, RoundedCornerShape(24.dp)),
                onValueChange = {
                    if (workout.name == "All") workout.id = null
                    uiState.searchQuery = it
                    screenModel.getExercises(workout.id, uiState.searchQuery)
                },
                text = uiState.searchQuery
            )
            Spacer(modifier = Modifier.height(12.dp))
            LazyColumn(Modifier.fillMaxSize()) {
                items(uiState.exercises) { exercise ->
                    ExerciseItem(exercise) { navigator.push(ExerciseDetailScreen(exercise)) }
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