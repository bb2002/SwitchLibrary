package kr.saintdev.switchlibrary.engine.auth.kakao

import android.content.Context
import com.kakao.auth.*
import kr.saintdev.switchlibrary.engine.auth.SwitchLibApplication

class KakaoSDKAdapter : KakaoAdapter() {
    override fun getApplicationConfig(): IApplicationConfig {
        return IApplicationConfig { SwitchLibApplication.getSwitchApplication() }
    }

    override fun getSessionConfig(): ISessionConfig {
        return object : ISessionConfig {
            override fun isSaveFormData() = true

            override fun getAuthTypes() = arrayOf(AuthType.KAKAO_ACCOUNT)

            override fun isSecureMode() = false

            override fun getApprovalType() = ApprovalType.INDIVIDUAL

            override fun isUsingWebviewTimer() = false
        }
    }
}