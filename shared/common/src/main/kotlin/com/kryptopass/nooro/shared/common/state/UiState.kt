package com.kryptopass.nooro.shared.common.state

// NOTE: represent state flow of app (good for UI as we want to display last loaded data)
sealed class UiState<out T : Any> {
    data object Empty : UiState<Nothing>()
    data object Loading : UiState<Nothing>()
    data class Error(val errorMessage: String) : UiState<Nothing>()
    data class Success<T : Any>(val data: T) : UiState<T>()
}