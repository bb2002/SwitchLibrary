package kr.saintdev.switchlibrary.engine.lib

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitObject {
    const val SERVER_HOST = "http://switchlib.saintdev.kr/"

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(SERVER_HOST)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}