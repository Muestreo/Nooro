package com.kryptopass.nooro.feature.search.composable

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
import com.kryptopass.nooro.feature.search.R
import com.kryptopass.nooro.feature.search.SearchModel

@Composable
fun SearchContent(searchModel: SearchModel) {
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
            model = "https:${searchModel.condition?.icon}",
            modifier = Modifier.size(128.dp),
            placeholder = painterResource(R.drawable.ic_placeholder)
        )

        // NOTE: use TextStyle.copy to dynamically adjust font sizes
        //       for temperature values based on screen size or data significance
        Text(
            style = MaterialTheme.typography.headlineLarge,
            text = "${searchModel.name}",
        )
        Text(
            modifier = Modifier.padding(vertical = 8.dp),
            style = MaterialTheme.typography.displayLarge,
            text = "${searchModel.tempF}°C"
        )

        SearchDetailsCard(
            feelsLikeTemp = searchModel.feelsLikeF ?: 0.0,
            humidity = searchModel.humidity ?: 0,
            uvIndex = searchModel.uv ?: 0.0,
        )
    }
}
