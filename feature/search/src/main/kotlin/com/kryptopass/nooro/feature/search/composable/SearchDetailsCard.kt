package com.kryptopass.nooro.feature.search.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SearchDetailsCard(humidity: Int, uvIndex: Double, feelsLikeTemp: Double) {
    Card(
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            SearchDetailItem(
                label = "Humidity",
                value = "$humidity%"
            )

            SearchDetailItem(
                label = "UV Index",
                value = "$uvIndex"
            )

            SearchDetailItem(
                label = "Feels Like",
                value = "$feelsLikeTemp°C"
            )
        }
    }
}
