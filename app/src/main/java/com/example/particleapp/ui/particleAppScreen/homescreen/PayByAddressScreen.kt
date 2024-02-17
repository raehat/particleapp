package com.example.particleapp.ui.particleAppScreen.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.particleapp.R
import com.example.particleapp.ui.particleAppScreen.ParticleAppViewModel
import com.example.particleapp.ui.particleAppScreen.Screen
import network.particle.chains.ChainInfo

@Composable
fun PayByAddressScreen(navController: NavHostController, viewModel: ParticleAppViewModel) {
    Column(
        modifier = Modifier
        .padding(top = 40.dp, start = 16.dp, end = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderScreen(onBackPressed = { navController.popBackStack() })
        ReceivingAddressCard(viewModel)
        AmountToBeSentCard(navController, viewModel)
        Spacer(modifier = Modifier.height(10.dp))
        SourceChainCard(viewModel, navController)
        DestinationChainCard(viewModel, navController)
        Spacer(modifier = Modifier.height(20.dp))
        SendCryptoButton()
    }
}

@Composable
fun SendCryptoButton() {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        onClick = {  }) {
        Text(text = "Send", fontSize = 16.sp)
    }
}

@Composable
fun DestinationChainCard(viewModel: ParticleAppViewModel, navController: NavHostController) {
//    val destionationChainInfo = ChainInfo.getChain(viewModel.qrCodeData.chainId.toLong(), viewModel.qrCodeData.chainName)
//    if (destionationChainInfo != null)
//        viewModel.destinationChainInfo = destionationChainInfo
    Column {
        Text(modifier = Modifier
            .padding(horizontal = 16.dp),
            text = "Destination Chain")
        Spacer(modifier = Modifier.height(3.dp))
        Card(
            modifier = Modifier
                .padding(16.dp)
                .clickable { navController.navigate(Screen.SelectDestinationChainScreen) }
            ,
            elevation = CardDefaults.cardElevation(10.dp),
            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background)
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)) {
                AsyncImage(model = ChainInfo.getChain(
                    viewModel.paymentData.chainId.toLong(),
                    viewModel.paymentData.chainName
                )?.icon, contentDescription = null)
                Spacer(modifier = Modifier.width(15.dp))
                Text(text = ChainInfo.getChain(
                    viewModel.paymentData.chainId.toLong(),
                    viewModel.paymentData.chainName
                )?.fullname.toString())
            }
        }
    }
}

@Composable
fun SourceChainCard(viewModel: ParticleAppViewModel, navController: NavHostController) {
    Column {
        Text(modifier = Modifier
            .padding(horizontal = 16.dp),
            text = "Source Chain")
        Spacer(modifier = Modifier.height(3.dp))
        Card(
            modifier = Modifier
                .padding(16.dp)
                .clickable { navController.navigate(Screen.SwitchChainScreen) }
            ,
            elevation = CardDefaults.cardElevation(10.dp),
            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background)
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)) {
                AsyncImage(model = viewModel.particleNetwork().chainInfo.icon, contentDescription = null)
                Spacer(modifier = Modifier.width(15.dp))
                Text(text = viewModel.particleNetwork().chainInfo.fullname)
            }
        }
    }
}

@Composable
fun AmountToBeSentCard(navController: NavHostController, viewModel: ParticleAppViewModel) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        var amountToBeSent by remember { mutableStateOf(viewModel.paymentData.amount) }
        OutlinedTextField(
            value = amountToBeSent,
            onValueChange = { amountToBeSent = it },
            placeholder = { Text("Amount") },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier
                .padding(16.dp)
                .weight(0.75f)
        )

        var nativeTokenSelected by remember { mutableStateOf(true) }
        Column(
            modifier = Modifier
                .weight(0.25f)
                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .clickable { nativeTokenSelected = !nativeTokenSelected },
                horizontalArrangement = Arrangement.Center
            ) {
                if (nativeTokenSelected) {
                    AsyncImage(model = viewModel.particleNetwork().chainInfo.icon, contentDescription = null)
                    Spacer(modifier = Modifier.width(15.dp))
                    Text(text = viewModel.particleNetwork().chainInfo.nativeCurrency.symbol)
                }
                else {
                    Image(
                        painter = painterResource(id = R.drawable.usdc_logo),
                        contentDescription = null,
                        modifier = Modifier
                            .size(22.dp)
                    )
                    Spacer(modifier = Modifier.width(15.dp))
                    Text(text = "USDC")
                }
            }
            Spacer(modifier = Modifier.height(7.dp))
            Text(text = "*click to switch", fontSize = 10.sp)
        }
    }
}

@Composable
private fun ReceivingAddressCard(viewModel: ParticleAppViewModel) {
    var receivingAddress by remember { mutableStateOf(viewModel.paymentData.address) }
    OutlinedTextField(
        value = receivingAddress,
        onValueChange = { receivingAddress = it },
        placeholder = { Text("Receiving Address") },
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        shape = RoundedCornerShape(30.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}