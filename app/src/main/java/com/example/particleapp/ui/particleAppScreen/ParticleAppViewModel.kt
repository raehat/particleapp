package com.example.particleapp.ui.particleAppScreen

import androidx.lifecycle.ViewModel
import com.particle.base.ParticleNetwork
import com.particle.base.data.ErrorInfo
import com.particle.base.data.WebOutput
import com.particle.base.data.WebServiceCallback
import com.particle.base.model.LoginType
import com.particle.base.model.SupportAuthType
import com.particle.base.model.UserInfo
import com.particle.network.ParticleNetworkAuth.login
import com.particle.network.ParticleNetworkAuth.logout

class ParticleAppViewModel : ViewModel() {
    fun login(onLoginSuccessful : () -> Unit, onLoginFailed : (String) -> Unit) {
        ParticleNetwork.login(
            loginType = LoginType.EMAIL,
            account = "",
            supportAuthTypeValues = SupportAuthType.ALL.value,
            loginCallback = object : WebServiceCallback<UserInfo> {
                override fun success(output: UserInfo) {
                    onLoginSuccessful()
                }

                override fun failure(errMsg: ErrorInfo) {
                    onLoginFailed(errMsg.message)
                }
            }
        )
    }

    fun logout(onLogoutSuccessful : () -> Unit, onLogoutFailed : (String) -> Unit) {
        ParticleNetwork.logout(object : WebServiceCallback<WebOutput> {
            override fun success(output: WebOutput) {
                onLogoutSuccessful()
            }

            override fun failure(errMsg: ErrorInfo) {
                onLogoutFailed(errMsg.message)
            }
        })
    }
}