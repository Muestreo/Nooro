package com.kryptopass.nooro.feature.home.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kryptopass.nooro.shared.common.theme.LocalComponentBackgroundColor

@Composable
fun HomeBar(onSearch: (String) -> Unit) {
    var searchQuery by remember { mutableStateOf("") }

    val backgroundColor = LocalComponentBackgroundColor.current

    TextField(
        value = searchQuery,
        onValueChange = { searchQuery = it },
        placeholder = { Text(text = "Search for a city") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp),
        maxLines = 1,
        trailingIcon = {
            IconButton(onClick = { onSearch(searchQuery) }) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
            }
        },
        singleLine = true,
        shape = RoundedCornerShape(16.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = backgroundColor,
            unfocusedContainerColor = backgroundColor,
            disabledContainerColor = backgroundColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                onSearch(searchQuery)
            }
        )
    )
}

@Preview(showBackground = true)
@Composable
fun HomeBarPreview() {
    HomeBar(onSearch = {})
}
