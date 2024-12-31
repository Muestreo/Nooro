package com.kryptopass.nooro.feature.home.composable

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kryptopass.nooro.shared.common.theme.LocalComponentBackgroundColor
import com.kryptopass.nooro.shared.common.theme.NooroTheme

@Composable
fun HomeDetailsCard(
    humidity: Int,
    uvIndex: Double,
    feelsLikeTemp: Double
) {
    val backgroundColor = LocalComponentBackgroundColor.current

    Card(
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            HomeDetailItem(
                label = "Humidity",
                value = "$humidity%"
            )

            HomeDetailItem(
                label = "UV Index",
                value = "$uvIndex"
            )

            HomeDetailItem(
                label = "Feels Like",
                value = "$feelsLikeTemp°C"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeDetailsCardPreview() {
    NooroTheme {
        HomeDetailsCard(
            humidity = 50,
            uvIndex = 5.0,
            feelsLikeTemp = 20.0
        )
    }
}
