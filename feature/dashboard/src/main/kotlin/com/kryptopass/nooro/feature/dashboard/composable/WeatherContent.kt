package com.kryptopass.nooro.feature.dashboard.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.kryptopass.nooro.feature.dashboard.R
import com.kryptopass.nooro.feature.dashboard.WeatherModel

@Composable
fun WeatherContent(weatherModel: WeatherModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AsyncImage(
            contentDescription = "Weather Icon",
            contentScale = ContentScale.Fit,
            error = painterResource(R.drawable.ic_placeholder),
            model = "https:${weatherModel.condition?.icon}",
            modifier = Modifier.size(128.dp),
            placeholder = painterResource(R.drawable.ic_placeholder)
        )

        Text(
            style = MaterialTheme.typography.headlineLarge,
            text = "${weatherModel.name}",
        )

        Text(
            modifier = Modifier.padding(vertical = 8.dp),
            style = MaterialTheme.typography.displayLarge,
            text = "${weatherModel.tempC}°C"
        )

        WeatherDetailsCard(
            feelsLikeTemp = weatherModel.feelsLikeF ?: 0.0,
            humidity = weatherModel.humidity ?: 0,
            uvIndex = weatherModel.uv ?: 0.0,
        )
    }
}
