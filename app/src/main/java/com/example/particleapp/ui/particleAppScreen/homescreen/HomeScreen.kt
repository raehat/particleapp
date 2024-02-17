package com.example.particleapp.ui.particleAppScreen.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavHostController
import com.example.particleapp.R
import com.example.particleapp.ui.particleAppScreen.ParticleAppViewModel
import com.example.particleapp.ui.particleAppScreen.Screen
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: ParticleAppViewModel,
    showToast: (String) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header()
        ImageHomeScreen()
        OptionsHomeScreen()
    }
}

@Composable
fun OptionsHomeScreen() {
    Row(
        modifier = Modifier
            .padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        CardButton { PayByQrCodeButton() }
        CardButton { PayByAddressButton() }
        CardButton { BuyCryptoButton() }
        CardButton { SellCryptoButton() }
    }
}

@Composable
fun SellCryptoButton() {
    Button(
        onClick = {  },
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        modifier = Modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = R.drawable.transfer_logo),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface
            )
            Text(modifier = Modifier.padding(top = 10.dp),
                text = "Sell\ncrypto",
                fontSize = 10.sp,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center)
        }
    }
}

@Composable
fun BuyCryptoButton() {
    Button(
        onClick = {  },
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        modifier = Modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = R.drawable.transfer_logo),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface
            )
            Text(modifier = Modifier.padding(top = 10.dp),
                text = "Buy\ncrypto",
                fontSize = 10.sp,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun PayByAddressButton() {
    Button(
        onClick = {  },
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        modifier = Modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = R.drawable.bank_logo),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface
            )
            Text(modifier = Modifier.padding(top = 10.dp),
                text = "Pay by\naddress",
                fontSize = 10.sp,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun PayByQrCodeButton() {
    Button(
        onClick = {  },
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        modifier = Modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = R.drawable.qr_code),
                contentDescription = null,
                modifier = Modifier.size(30.dp),
                tint = MaterialTheme.colorScheme.onSurface
            )
            Text(modifier = Modifier.padding(top = 10.dp),
                text = "Pay by\nQR code",
                fontSize = 10.sp,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center)
        }
    }
}

@Composable
fun CardButton(content: @Composable () -> Unit) {
    Card(
        modifier = Modifier
            .padding(horizontal = 5.dp),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
        ) {
            content()
        }
    }
}

@Composable
fun Header() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(top = 40.dp, start = 20.dp, bottom = 20.dp)
    ) {
        AddressEditText()
        MyAccountButton()
    }
}

@Composable
fun MyAccountButton() {
    Button(
        onClick = {  },
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        modifier = Modifier
    ) {
        Icon(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = "Account Icon",
            modifier = Modifier.size(48.dp),
            tint = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
fun AddressEditText() {
    var text by remember { mutableStateOf(TextFieldValue()) }
    OutlinedTextField(
        value = text,
        onValueChange = { newText ->
            text = newText
        },
        placeholder = { Text(text = "Pay by address", fontSize = 14.sp) },
        modifier = Modifier
            .height(50.dp),
        shape = RoundedCornerShape(30.dp)
    )
}

@Composable
private fun LogoutButton(
    viewModel: ParticleAppViewModel,
    navController: NavHostController,
    showToast: (String) -> Unit
) {
    Button(onClick = {
        viewModel.logout(
            onLogoutSuccessful = { navController.navigate(Screen.LoginScreen) },
            onLogoutFailed = { showToast("Logout Failed $it") }
        )
    }) {
        Text(text = "Log Out")
    }
}

@Composable
private fun ImageHomeScreen() {
    Image(
        modifier = Modifier
            .fillMaxWidth(),
        painter = painterResource(id = R.drawable.home_screen_logo),
        contentDescription = null
    )
}