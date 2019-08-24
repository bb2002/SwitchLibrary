package kr.saintdev.switchlibrary.engine.lib

import retrofit2.Retrofit

object RetrofitObject {
    const val SERVER_HOST = "http://localhost/"

    val retrofit: Retrofit = Retrofit.Builder().baseUrl(SERVER_HOST).build()
}