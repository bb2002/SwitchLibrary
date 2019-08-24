package kr.saintdev.switchlibrary.engine.auth.switchlib

import android.widget.Switch
import com.google.gson.JsonObject
import com.kakao.usermgmt.response.MeV2Response
import kr.saintdev.switchlibrary.engine.lib.RetrofitObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SwitchLibAuthManager private constructor() {
    companion object {
        private var instance: SwitchLibAuthManager? = null  // Current Auth Instance
        private var serverToken: String? = null             // Current Server Request Token.

        fun getInstance() : SwitchLibAuthManager {
            if(instance == null) {
                instance = SwitchLibAuthManager()
            }
            return instance!!
        }

        fun getCurrentToken() : String? {
            return serverToken
        }
    }

    /**
     * @Date 08.24 2019
     * SwitchLibrary 서버에 인증 요청을 합니다.
     */
    fun tryAuth(kakaoAccount: MeV2Response, callback: Callback<SwitchLibAuthContainer>) {
        val authService = RetrofitObject.retrofit.create(SwitchLibAuthService::class.java)
        val data = JsonObject()
        data.addProperty("uniqid", kakaoAccount.id)
        data.addProperty("nickname", kakaoAccount.nickname)
        data.addProperty("profile_img", kakaoAccount.profileImagePath)
        data.addProperty("thumb_img", kakaoAccount.thumbnailImagePath)
        data.addProperty("email", kakaoAccount.kakaoAccount?.email)
        val call = authService.requestAuthForKakao(data.toString())
        call.enqueue(callback)
    }

    /**
     * @Date 08.24 2019
     * 토큰 값을 저장합니다.
     */
    fun refreshToken(newToken: String) {
        serverToken = newToken
    }
}