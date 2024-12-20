package com.kryptopass.nooro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kryptopass.nooro.core.common.nav.HOME_SCREEN
import com.kryptopass.nooro.feature.dashboard.composable.HomeScreen
import com.kryptopass.nooro.ui.theme.NooroTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NooroActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NooroTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    App(navController = rememberNavController())
                }
            }
        }
    }
}

@Composable
fun App(navController: NavHostController) {
    NavHost(navController, startDestination = HOME_SCREEN) {
        composable(route = HOME_SCREEN) {
            HomeScreen(hiltViewModel())
        }
    }
}
