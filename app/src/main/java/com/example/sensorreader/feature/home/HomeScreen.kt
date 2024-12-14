package com.example.sensorreader.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.sensorreader.data.model.SensorDataModel
import com.example.sensorreader.feature.common.CenterAlignedText

@Composable
fun HomeScreenRoute() {
    HomeScreen()
}

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val uiState = viewModel.state.collectAsState()

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_STOP -> viewModel.onStopReceivingDataClick()
                else -> {}
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    when (val state = uiState.value) {
        is HomeUiState.Idle -> HomeIdle {
            viewModel.onFetchButtonClick()
        }
        is HomeUiState.Content -> HomeContent(state = state) {
            viewModel.onStopReceivingDataClick()
        }
    }
}

@Composable
private fun HomeContent(
    modifier: Modifier = Modifier,
    state: HomeUiState.Content,
    onButtonClick: () -> Unit
) {
    Column(
        modifier = modifier
            .systemBarsPadding()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                modifier = modifier.padding(horizontal = 32.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiary),
                onClick = { onButtonClick() }
            ) {
                CenterAlignedText(text = "Stop fetching sensor data")
            }
        }
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 32.dp)
        ) {
            Column(modifier = modifier.weight(1f)) {
                CenterAlignedText(text = "Accelerometer data:")
                CenterAlignedText(text = state.accelerometerData.x.toString())
            }
            Column(modifier = modifier.weight(1f)) {
                CenterAlignedText(text = "Gyroscope data:")
                CenterAlignedText(text = state.gyroscopeData.x.toString())
            }
        }
    }
}

@Composable
fun HomeIdle(
    modifier: Modifier = Modifier,
    onButtonClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .systemBarsPadding()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                modifier = modifier.padding(horizontal = 32.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiary),
                onClick = { onButtonClick() }
            ) {
                CenterAlignedText(text = "Fetch sensor data")
            }
        }
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 32.dp)
        ) {
            Column(modifier = modifier.weight(1f)) {
                CenterAlignedText(text = "Accelerometer data:")
            }
            Column(modifier = modifier.weight(1f)) {
                CenterAlignedText(text ="Gyroscope data:")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenIdlePreview(modifier: Modifier = Modifier) {
    HomeIdle {
        // no-op
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenContentPreview(modifier: Modifier = Modifier) {
    HomeContent(
        state = HomeUiState.Content(
            accelerometerData = SensorDataModel("1", Long.MIN_VALUE, "accelerometer", -0.22f, 1.1f, 0.99f),
            gyroscopeData = SensorDataModel("2", Long.MAX_VALUE, "gyroscope", 0.55f, 0.3f, 0.2f),
        )
    ) {
        // no-op
    }
}