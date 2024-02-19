package com.sukhralia.workout.feature.workout.presentation.exercisedetail.screen

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import cafe.adriel.voyager.core.screen.Screen
import coil.compose.AsyncImage
import com.sukhralia.workout.R
import com.sukhralia.workout.feature.workout.domain.model.Exercise
import com.sukhralia.workout.util.convertToNumberedPoints
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield
import kotlin.math.absoluteValue

data class ExerciseDetailScreen(val exercise: Exercise = Exercise()) : Screen {

    @OptIn(ExperimentalFoundationApi::class)
    @Preview
    @Composable
    override fun Content() {

        val pagerState = rememberPagerState(
            initialPage = 0,
            pageCount = { exercise.images?.size ?: 0 }
        )

        LaunchedEffect(Unit) {
            while (true) {
                yield()
                delay(2000)
                pagerState.animateScrollToPage(
                    page = (pagerState.currentPage + 1) % (pagerState.pageCount),
                    animationSpec = tween(600)
                )
            }
        }

        Column(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(8.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = exercise.name ?: "",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge.copy(fontSize = 32.sp),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(26.dp))
            HorizontalPager(
                state = pagerState, modifier = Modifier.wrapContentSize(),
                contentPadding = PaddingValues(start = 24.dp, end = 24.dp),
            ) { page ->
                val nextImg = exercise.images?.getOrNull(page) ?: "https://picsum.photos/500/500"

                AsyncImage(
                    model = nextImg,
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .padding(horizontal = 6.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .graphicsLayer {
                            val pageOffset = (
                                    (pagerState.currentPage - page) + pagerState
                                        .currentPageOffsetFraction
                                    ).absoluteValue
                            alpha = lerp(
                                start = 0.5f,
                                stop = 1f,
                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            )
                        }
                )

            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(pagerState.pageCount) { iteration ->
                    val color =
                        if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                            .clip(CircleShape)
                            .background(color)
                            .size(10.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = exercise.description ?: "",
                style = MaterialTheme.typography.bodyMedium,
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = stringResource(id = R.string.instructions),
                style = MaterialTheme.typography.titleLarge.copy(fontSize = 20.sp),
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = exercise.instruction?.convertToNumberedPoints() ?: "",
                style = MaterialTheme.typography.bodyMedium,
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = stringResource(id = R.string.remember),
                style = MaterialTheme.typography.titleLarge.copy(fontSize = 20.sp),
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = exercise.note?.convertToNumberedPoints() ?: "",
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}
