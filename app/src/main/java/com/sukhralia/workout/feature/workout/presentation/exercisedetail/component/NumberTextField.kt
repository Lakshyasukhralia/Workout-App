package com.sukhralia.workout.feature.workout.presentation.exercisedetail.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun NumberTextField(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .border(.5.dp, Color.LightGray, RoundedCornerShape(16.dp)),
    onValueChange: (value: String) -> Unit = {},
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
    maxChar: Int = 30,
    text: String = "",
    label: String = "",
    trailingIcon: ImageVector? = null
) {
    var textValue by remember { mutableStateOf("") }
    if (text.isNotEmpty() && textValue != text) textValue = text

    TextField(value = textValue,
        textStyle = MaterialTheme.typography.titleMedium.copy(
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.inversePrimary,
        ),
        onValueChange = {
            if (it.length <= maxChar) {
                onValueChange.invoke(it)
                textValue = it
            }
        },
        maxLines = 1,
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = TextFieldDefaults.colors(
            disabledTextColor = Color.Transparent,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            cursorColor = MaterialTheme.colorScheme.inversePrimary
        ),
        keyboardOptions = keyboardOptions,
        placeholder = {
            Box(
                modifier = Modifier.fillMaxWidth(), // Make the Box fill the maximum width
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .alpha(.5f),
                    text = label,
                    style = MaterialTheme.typography.titleMedium.copy(
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.inversePrimary,
                    ),
                    maxLines = 1,
                    textAlign = TextAlign.Center,
                )
            }
        })
}
