package com.sukhralia.workout.feature.workout.presentation.exercisedetail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sukhralia.workout.R

@Composable
fun ExerciseTrackerItem(
    modifier: Modifier = Modifier,
    onField1Change: (String) -> Unit = {},
    onField2Change: (String) -> Unit = {}
) {
    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(12.dp),
        modifier = modifier.fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.onPrimary)
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            NumberTextField(
                label = stringResource(id = R.string.weight),
                maxChar = 3,
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.5.dp, MaterialTheme.colorScheme.inversePrimary, RoundedCornerShape(4.dp))
                    .weight(.4f),
                onValueChange = {
                    onField1Change.invoke(it)
                },
            )
            Spacer(modifier = Modifier.width(12.dp))
            NumberTextField(
                label = stringResource(id = R.string.reps),
                maxChar = 3,
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.5.dp, MaterialTheme.colorScheme.inversePrimary, RoundedCornerShape(4.dp))
                    .weight(.4f),
                onValueChange = {
                    onField2Change.invoke(it)
                },
            )
        }
    }
}