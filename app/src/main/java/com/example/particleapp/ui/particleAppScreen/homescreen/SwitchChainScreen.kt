package com.example.particleapp.ui.particleAppScreen.homescreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.particleapp.ui.particleAppScreen.ParticleAppViewModel
import com.example.particleapp.ui.particleAppScreen.Screen
import network.particle.chains.ChainInfo

@Composable
fun SwitchChainScreen(
    navController: NavHostController,
    viewModel: ParticleAppViewModel,
    showToast: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(top = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderScreen(onBackPressed = { navController.popBackStack() })
        ChainListWithSearch(
            items = ChainInfo.getAllChains(),
            onClick = { chainInfo ->
                viewModel.switchChain(
                    chainInfo,
                    onSuccess = {
                        showToast("Chain switched to ${chainInfo.fullname}")
                        navController.navigate(Screen.HomeScreen)
                    },
                    onFailure = { showToast("Cannot Switch Chain") })
            }
        )
    }
}