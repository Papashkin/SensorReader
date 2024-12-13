package com.example.sensorreader.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomeScreenRoute() {
    HomeScreenContent()
}

//TODO add ViewModel

@Composable
private fun HomeScreenContent(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState = viewModel.state.collectAsState()

    val state = uiState.value

    Column(
        modifier = modifier
            .fillMaxSize()
            .systemBarsPadding()
            .padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
            ,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                modifier = Modifier,
                onClick = { viewModel.onFetchButtonClick(state.isIdle()) }
            ) {
                if (state.isIdle()) {
                    Text("Fetch sensor data")
                } else {
                    Text("Stop fetching sensor data")
                }
            }
        }
        Row(modifier = modifier.fillMaxWidth().padding(top = 32.dp)) {
            Column(modifier = modifier.weight(1f)) {
                Text("Accelerometer data:")
                Text(text = if (state.isIdle()) "-" else "")
            }
            Column(modifier = modifier.weight(1f)) {
                Text("Gyroscope data:")
                Text(text = if (state.isIdle()) "-" else "")
            }
        }
    }
}


@Preview
@Composable
fun HomeScreenPreview(modifier: Modifier = Modifier) {
    HomeScreenRoute()
}