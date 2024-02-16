package com.example.particleapp.ui.particleAppScreen

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.particleapp.ui.particleAppScreen.homescreen.HomeScreen
import com.example.particleapp.ui.particleAppScreen.loginscreen.LoginScreen
import com.example.particleapp.ui.particleAppScreen.splashscreen.SplashScreen
import com.example.particleapp.ui.theme.ParticleAppTheme

class ParticleApp : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel : ParticleAppViewModel = ViewModelProvider(this)[ParticleAppViewModel::class.java]
        setContent {
            ParticleAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val showToast : (String) -> Unit = {
                        Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                    }
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Screen.SplashScreen) {
                        composable(route = Screen.SplashScreen) {
                            ColumnScreen { SplashScreen(navController) }
                        }
                        composable(route = Screen.LoginScreen) {
                            ColumnScreen { LoginScreen(navController, viewModel, showToast) }
                        }
                        composable(route = Screen.HomeScreen) {
                            ColumnScreen { HomeScreen(navController) }
                        }
                    }
                }
            }
        }
    }
}