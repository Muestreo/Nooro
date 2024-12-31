package com.kryptopass.nooro.feature.home.composable

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.kryptopass.nooro.feature.home.ConditionModel
import com.kryptopass.nooro.feature.home.HomeModel
import com.kryptopass.nooro.feature.home.R
import com.kryptopass.nooro.shared.common.theme.NooroTheme

@Composable
fun HomeContent(homeModel: HomeModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AsyncImage(
            contentDescription = "Weather Icon",
            contentScale = ContentScale.Fit,
            error = painterResource(R.drawable.ic_placeholder),
            model = "https:${homeModel.condition?.icon}",
            modifier = Modifier.size(128.dp),
            placeholder = painterResource(R.drawable.ic_placeholder)
        )

        // NOTE: use TextStyle.copy to dynamically adjust font sizes
        //       for temperature values based on screen size or data significance
        Text(
            style = MaterialTheme.typography.headlineLarge,
            text = "${homeModel.name}",
        )
        Text(
            modifier = Modifier.padding(vertical = 8.dp),
            style = MaterialTheme.typography.displayLarge,
            text = "${homeModel.tempC}°C"
        )

        HomeDetailsCard(
            feelsLikeTemp = homeModel.feelsLikeC ?: 0.0,
            humidity = homeModel.humidity ?: 0,
            uvIndex = homeModel.uv ?: 0.0,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeContentPreview() {
    NooroTheme {
        HomeContent(
            HomeModel(
                condition = ConditionModel(
                    icon = "//cdn.weatherapi.com/weather/64x64/day/113.png"
                ),
                feelsLikeC = 18.0,
                feelsLikeF = 64.4,
                humidity = 50,
                name = "London",
                tempC = 20.0,
                tempF = 68.0,
                uv = 2.0
            )
        )
    }
}
