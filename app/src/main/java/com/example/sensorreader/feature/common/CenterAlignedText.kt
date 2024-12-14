package com.example.sensorreader.feature.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun CenterAlignedText(
    modifier: Modifier = Modifier,
    text: String,
    ) {
    Text(text, textAlign = TextAlign.Center, modifier = modifier.fillMaxWidth())
}