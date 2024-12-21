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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.kryptopass.nooro.core.domain.entity.Weather
import com.kryptopass.nooro.feature.dashboard.R

@Composable
fun WeatherContent(weather: Weather) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AsyncImage(
            model = "https:${weather.current?.condition?.icon}",
            contentDescription = "Weather Icon",
            placeholder = painterResource(R.drawable.ic_placeholder),
            error = painterResource(R.drawable.ic_placeholder),
            modifier = Modifier.size(128.dp)
        )

        Text(
            text = "${weather.location?.name}",
            style = MaterialTheme.typography.headlineLarge
        )

        Text(
            text = "${weather.current?.tempC}°C",
            style = MaterialTheme.typography.displayLarge,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        WeatherDetailsCard(
            humidity = weather.current?.humidity ?: 0,
            uvIndex = weather.current?.uv ?: 0.0,
            feelsLikeTemp = weather.current?.feelslikeC ?: 0.0
        )
    }
}
