package com.kryptopass.nooro.feature.search.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.kryptopass.nooro.feature.search.ConditionModel
import com.kryptopass.nooro.feature.search.R
import com.kryptopass.nooro.feature.search.SearchModel
import com.kryptopass.nooro.shared.common.theme.LocalComponentBackgroundColor
import com.kryptopass.nooro.shared.common.theme.NooroTheme

@Composable
fun SearchCityCard(
    searchModel: SearchModel,
    onClick: () -> Unit
) {
    val backgroundColor = LocalComponentBackgroundColor.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor)
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 32.dp, vertical = 8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = searchModel.name ?: "",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "${searchModel.tempF ?: "--"}°F",
                    style = MaterialTheme.typography.headlineMedium
                )
            }

            AsyncImage(
                contentDescription = "Weather Icon",
                contentScale = ContentScale.Fit,
                error = painterResource(R.drawable.ic_placeholder),
                model = "https:${searchModel.condition?.icon}",
                modifier = Modifier.size(64.dp),
                placeholder = painterResource(R.drawable.ic_placeholder)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchCityCardPreview() {
    NooroTheme {
        SearchCityCard(
            searchModel = SearchModel(
                condition = ConditionModel(
                    icon = "//cdn.weatherapi.com/weather/64x64/day/113.png"
                ),
                name = "London",
                tempC = 20.0,
                tempF = 70.0
            ),
            onClick = {}
        )
    }
}
