package kr.saintdev.switchlibrary.engine.auth.switchlib

import retrofit2.Call
import retrofit2.http.*

interface SwitchLibAuthService {
    @POST("auth/kakaologin")
    fun requestAuthForKakao(@Query("id") userId: String, @Query("nickname") nickname: String) : Call<SwitchLibAuthContainer>
}