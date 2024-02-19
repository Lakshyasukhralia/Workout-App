package com.sukhralia.workout.feature.workout.presentation.exercise.component

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.sukhralia.workout.feature.workout.domain.model.Exercise

@Composable
fun ExerciseItem(exercise: Exercise, onClick: () -> Unit) {
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
                    exercise.images?.getOrNull(0) ?: "https://picsum.photos/500/500",
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
            Column(
                Modifier
                    .align(Alignment.Top)
                    .weight(7f)
                    .padding(horizontal = 8.dp)
            ) {
                Text(
                    text = exercise.name ?: "",
                    style = MaterialTheme.typography.titleMedium.copy(fontSize = 22.sp),
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = exercise.description?.take(100) ?: "",
                    style = MaterialTheme.typography.bodySmall,
                )
            }
            Icon(
                imageVector = Icons.Filled.KeyboardArrowRight,
                contentDescription = "Go",
                modifier = Modifier
                    .size(42.dp)
                    .align(Alignment.CenterVertically)
                    .weight(1f)
            )
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