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
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.kryptopass.nooro.core.domain.entity.Weather

@Composable
fun WeatherContent(weather: Weather) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "City: ${weather.location?.name}",
            style = MaterialTheme.typography.headlineLarge
        )
        Text(text = "Temperature: ${weather.current?.tempC}°C")
        Text(text = "Condition: ${weather.current?.condition?.text}")
        AsyncImage(
            model = "https:${weather.current?.condition?.icon}",
            contentDescription = "Weather Icon",
            modifier = Modifier.size(64.dp)
        )
    }
}
