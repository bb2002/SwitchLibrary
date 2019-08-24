package kr.saintdev.switchlibrary.engine.auth.switchlib

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path

interface SwitchLibAuthService {
    @GET("auth/login")
    fun requestAuthForKakao(@Body data: String) : Call<SwitchLibAuthContainer>
}