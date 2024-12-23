package com.kryptopass.nooro.shared.common.state

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.kryptopass.nooro.shared.common.composable.EmptyStateScreen
import com.kryptopass.nooro.shared.common.composable.ErrorScreen
import com.kryptopass.nooro.shared.common.composable.LoadingScreen

@Composable
fun <T : Any> CommonScreen(
    state: UiState<T>,
    onRetry: () -> Unit = {},
    onSuccess: @Composable (T) -> Unit
) {
    when (state) {
        is UiState.Loading -> {
            LoadingScreen()
        }

        is UiState.Error -> {
            ErrorScreen(
                message = state.errorMessage,
                onRetry = onRetry
            )
        }

        is UiState.Success -> {
            onSuccess(state.data)
        }

        is UiState.Empty -> {
            EmptyStateScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CommonScreenPreview() {
    CommonScreen(
        state = UiState.Empty,
        onSuccess = {}
    )
}
