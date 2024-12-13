package com.example.sensorreader.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreenRoute() {
    HomeScreenContent()
}

//TODO add ViewModel

@Composable
private fun HomeScreenContent(
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxSize().systemBarsPadding()) {
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                modifier = Modifier,
                onClick = {}
            ) {
                Text("Fetch sensor data")
            }
        }
        Row(modifier = modifier.fillMaxWidth()) {
            Column(modifier = modifier.weight(1f)) {
                Text("Accelerometer data:")
                Text("-")
            }
            Column(modifier = modifier.weight(1f)) {
                Text("Gyroscope data:")
                Text("-")
            }
        }
    }
}


@Preview
@Composable
fun HomeScreenPreview(modifier: Modifier = Modifier) {
    HomeScreenRoute()
}