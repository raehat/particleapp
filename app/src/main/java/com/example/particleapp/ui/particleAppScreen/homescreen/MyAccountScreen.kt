package com.example.particleapp.ui.particleAppScreen.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.particleapp.ui.particleAppScreen.ParticleAppViewModel
import com.example.particleapp.utils.QrCodeUtils
import com.particle.network.ParticleNetworkAuth.getUserInfo

@Composable
fun MyAccountScreen(navController: NavHostController, viewModel: ParticleAppViewModel) {
    Column(
        modifier = Modifier
            .padding(top = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderMyAccountScreen(onBackPressed = { navController.popBackStack() })
        UserInfoCard(viewModel)
        CryptoInfoCard(viewModel)
    }
}

@Composable
fun CryptoInfoCard(viewModel: ParticleAppViewModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = viewModel.particleNetwork().chainInfo.fullname)
                Spacer(modifier = Modifier.width(15.dp))
                Image(
                    painter = rememberAsyncImagePainter(model = viewModel.particleNetwork().chainInfo.icon),
                    contentDescription = null
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp, horizontal = 25.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        bitmap = QrCodeUtils.getQrCodeBitmap("lolol"),
                        contentDescription = null
                    )
                    Text(modifier = Modifier.padding(top = 10.dp), text = "*scan this QR code to receive crypto", fontSize = 10.sp)
                }
                Column(modifier = Modifier.padding(horizontal = 10.dp)) {
                    Text(text = "Balance", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Text(text = "32" + viewModel.particleNetwork().chainInfo.nativeCurrency.symbol)
                    Text(text = "$48", fontStyle = FontStyle.Italic)
                }
            }
        }
    }
}

@Composable
fun UserInfoCard(viewModel: ParticleAppViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(text = viewModel.userInfomation?.name.toString())
            Text(text = viewModel.userLoginEmail().toString())
            Text(text = viewModel.address(), fontSize = 10.sp, fontStyle = FontStyle.Italic)
        }
        if (viewModel.avatar() != null) {
            Image(
                painter = rememberAsyncImagePainter(viewModel.avatar()),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        } else {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Account Icon",
                modifier = Modifier.size(80.dp),
                tint = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
fun HeaderMyAccountScreen(
    onBackPressed: () -> Unit = {},
    onMenuClicked: () -> Unit = {},
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(start = 16.dp, end = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Left arrow icon
        Icon(
            imageVector = Icons.Default.KeyboardArrowLeft,
            contentDescription = null, modifier = Modifier
                .size(24.dp)
                .clickable { onBackPressed() },
            tint = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.weight(1f))

        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = "Menu",
            tint = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .size(24.dp)
                .clickable { onMenuClicked() }
        )
    }
}