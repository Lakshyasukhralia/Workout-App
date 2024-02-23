package com.sukhralia.workout.feature.workout.presentation.exercisedetail.screen

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import com.sukhralia.workout.R
import com.sukhralia.workout.feature.workout.domain.model.Exercise
import com.sukhralia.workout.feature.workout.presentation.exercisedetail.component.ExerciseTrackerItem
import com.sukhralia.workout.feature.workout.presentation.exercisedetail.component.VideoPlayer
import com.sukhralia.workout.navigation.BaseScreen
import com.sukhralia.workout.navigation.tab.HistoryTab
import com.sukhralia.workout.util.convertToNumberedPoints
import kotlinx.coroutines.launch

data class ExerciseDetailScreen(val exercise: Exercise = Exercise()) : BaseScreen() {

    @SuppressLint("CoroutineCreationDuringComposition")
    @OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
    @Preview
    @Composable
    override fun Content() {

        var imgDrawable: Drawable? by remember { mutableStateOf(null) }
        val sheetState = rememberModalBottomSheetState()
        var showBottomSheet by remember { mutableStateOf(false) }

        val context = LocalContext.current
        val navigator = LocalTabNavigator.current
        val coroutineScope = rememberCoroutineScope()
        val snackbarHostState = remember { SnackbarHostState() }
        val screenModel = rememberScreenModel { ExerciseDetailScreenModel() }
        val uiState by screenModel.uiState.collectAsState()

        val keyboardController = LocalSoftwareKeyboardController.current
        val focusManager = LocalFocusManager.current


        val saveText = stringResource(id = R.string.saved_to_logs)
        val showText = stringResource(id = R.string.show)

        var wgt by remember { mutableStateOf("") }
        var reps by remember { mutableStateOf("") }

//        val imageLoader = ImageLoader.Builder(LocalContext.current)
//            .components {
//                if (SDK_INT >= 28) {
//                    add(ImageDecoderDecoder.Factory())
//                } else {
//                    add(GifDecoder.Factory())
//                }
//            }
//            .build()
//
//        val request = ImageRequest.Builder(LocalContext.current)
//            .data(exercise.demoImg)
//            .build()
//
//        LaunchedEffect(Unit) {
//            imgDrawable = imageLoader.execute(request).drawable
//        }
        Scaffold(
            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState)
            }) { pv ->

            Column(
                Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.inversePrimary)
                    .padding(pv)
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = exercise.name ?: "",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge.copy(fontSize = 32.sp),
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Spacer(modifier = Modifier.height(12.dp))
                Card(
                    Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.inversePrimary)
                        .padding(8.dp),
                    elevation = CardDefaults.cardElevation(12.dp),
                    shape = RoundedCornerShape(16.dp),
                ) {
                    Column(
                        Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.onPrimary)
                    ) {
//                        AsyncImage(
//                            model = imgDrawable,
//                            contentDescription = "",
//                            contentScale = ContentScale.Fit,
//                            modifier = Modifier.aspectRatio(1f)
//                        )
                        Box(Modifier.aspectRatio(1f)) {
                            exercise.demoVideo?.let { VideoPlayer(uri = it.toUri()) }
                        }
                        Column(
                            Modifier
                                .fillMaxSize()
                                .padding(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Info,
                                contentDescription = "Go",
                                modifier = Modifier
                                    .size(28.dp)
                                    .aspectRatio(1f)
                                    .clip(CircleShape)
                                    .align(Alignment.End)
                                    .clickable { showBottomSheet = true },
                                tint = MaterialTheme.colorScheme.inversePrimary
                            )

                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = exercise.description ?: "",
                                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp),
                                color = MaterialTheme.colorScheme.inversePrimary,
                                maxLines = 5,
                                overflow = TextOverflow.Ellipsis
                            )
                            Spacer(modifier = Modifier.weight(1f))
//                            Spacer(modifier = Modifier.height(22.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                ExerciseTrackerItem(
                                    Modifier
                                        .fillMaxWidth()
                                        .weight(.8f),
                                    onField1Change = { wgt = it },
                                    onField2Change = { reps = it }
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Card(
                                    modifier = Modifier,
                                    shape = RoundedCornerShape(12.dp),
                                    elevation = CardDefaults.cardElevation(4.dp),
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.Add,
                                        contentDescription = "Go",
                                        modifier = Modifier
                                            .size(44.dp)
                                            .background(MaterialTheme.colorScheme.inversePrimary)
                                            .aspectRatio(1f)
                                            .clickable {
                                                if (wgt.isNotEmpty() && reps.isNotEmpty()) {
                                                    exercise.wgt = wgt.toIntOrNull() ?: 0
                                                    exercise.reps = reps.toIntOrNull() ?: 0
                                                    keyboardController?.hide()
                                                    focusManager.clearFocus(true)
                                                    screenModel.saveExercise(exercise)
                                                    coroutineScope.launch {
                                                        val result = snackbarHostState
                                                            .showSnackbar(
                                                                message = saveText,
                                                                actionLabel = showText,
                                                                duration = SnackbarDuration.Short
                                                            )
                                                        when (result) {
                                                            SnackbarResult.ActionPerformed -> {
                                                                navigator.current = HistoryTab
                                                            }

                                                            SnackbarResult.Dismissed -> {
                                                            }
                                                        }
                                                    }
                                                }
                                            },
                                        tint = MaterialTheme.colorScheme.onPrimary
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(12.dp))
                        }
                    }
                }
                if (showBottomSheet)
                    ModalBottomSheet(
                        sheetState = sheetState,
                        onDismissRequest = { showBottomSheet = false }) {
                        Column(Modifier.padding(horizontal = 22.dp)) {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = stringResource(id = R.string.instructions),
                                style = MaterialTheme.typography.titleLarge.copy(fontSize = 26.sp),
                                textAlign = TextAlign.Center
                            )
                            Spacer(modifier = Modifier.height(32.dp))
                            Text(
                                text = exercise.instruction?.convertToNumberedPoints() ?: "",
                                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp)
                            )
                            Spacer(modifier = Modifier.height(18.dp))
                        }
                    }
            }
        }
    }
}


