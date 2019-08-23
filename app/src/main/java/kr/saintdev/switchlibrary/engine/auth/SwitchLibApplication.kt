package kr.saintdev.switchlibrary.engine.auth

import android.app.Application
import com.kakao.auth.KakaoSDK
import kr.saintdev.switchlibrary.engine.auth.kakao.KakaoSDKAdapter

class SwitchLibApplication : Application() {
    companion object {
        private var instance: SwitchLibApplication? = null

        fun getSwitchApplication() : SwitchLibApplication? {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
        KakaoSDK.init(KakaoSDKAdapter())
    }

    override fun onTerminate() {
        super.onTerminate()
        instance = null
    }
}