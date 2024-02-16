package com.example.particleapp.ui.particleAppScreen.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.particleapp.R
import com.example.particleapp.ui.buttons.ParticleAppButton

@Composable
fun HomeScreen(navController: NavHostController) {
    Image(painter = painterResource(id = R.drawable.home_screen_logo), contentDescription = null)
    Text(
        text = "To get started, please click on login. You will be redirected to the login page.",
        textAlign = TextAlign.Center
    )
    ParticleAppButton(buttonText = "Login")
}