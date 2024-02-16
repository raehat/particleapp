package com.example.particleapp.ui.particleAppScreen

import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.particle.base.CurrencyEnum
import com.particle.base.ParticleNetwork
import com.particle.base.ParticleNetwork.setFiatCoin
import com.particle.base.data.ErrorInfo
import com.particle.base.data.WebServiceCallback
import com.particle.base.model.LoginType
import com.particle.base.model.SupportAuthType
import com.particle.base.model.UserInfo
import com.particle.network.ParticleNetworkAuth.login
import com.particle.network.ParticleNetworkAuth.signTransaction

class ParticleAppViewModel : ViewModel() {
    fun login(onLoginSuccessful : () -> Unit, onLoginFailed : () -> Unit) {
        ParticleNetwork.login(
            loginType = LoginType.EMAIL,
            account = "",
            supportAuthTypeValues = SupportAuthType.ALL.value,
            loginCallback = object : WebServiceCallback<UserInfo> {
                override fun failure(errMsg: ErrorInfo) {
                    onLoginFailed()
                }

                override fun success(output: UserInfo) {
                    onLoginSuccessful()
                }
            }
        )
    }
}