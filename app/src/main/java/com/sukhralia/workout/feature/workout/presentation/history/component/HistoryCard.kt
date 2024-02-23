package com.sukhralia.workout.feature.workout.presentation.history.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.VideoFrameDecoder
import com.sukhralia.workout.feature.workout.domain.model.Exercise
import com.sukhralia.workout.util.formatDateTime
import java.util.Date

@Composable
fun HistoryCard(exercise: Exercise, onClick: () -> Unit) {

    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .components {
            add(VideoFrameDecoder.Factory())
        }
        .crossfade(true)
        .build()

    Column(
        Modifier
            .fillMaxSize()
            .clickable { onClick.invoke() }) {
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(Modifier.weight(2f)) {
                AsyncImage(
                    model = exercise.demoVideo,
                    imageLoader = imageLoader,
                    contentDescription = "",
                    modifier = Modifier
                        .aspectRatio(1f)
                        .border(
                            1.dp,
                            MaterialTheme.colorScheme.onPrimary,
                            CircleShape
                        )
                        .clip(CircleShape),
                    contentScale = ContentScale.FillBounds
                )
            }
            Spacer(modifier = Modifier.width(4.dp))
            Column(
                Modifier
                    .align(Alignment.Top)
                    .weight(8f)
                    .padding(horizontal = 8.dp)
                    .fillMaxSize(),
            ) {
                Text(
                    text = exercise.name ?: "",
                    style = MaterialTheme.typography.titleMedium.copy(fontSize = 22.sp),
                )
                Spacer(modifier = Modifier.weight(1f))
                Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "${exercise.wgt} kg",
                        style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp),
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Spacer(
                        modifier = Modifier
                            .height(24.dp)
                            .width(1.5.dp)
                            .background(MaterialTheme.colorScheme.onPrimary)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "${exercise.reps} reps",
                        style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp),
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Text(
                    text = formatDateTime(exercise.createDate ?: Date()),
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.End,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .padding(horizontal = 16.dp)
                .background(MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.2f))
        )
    }
}