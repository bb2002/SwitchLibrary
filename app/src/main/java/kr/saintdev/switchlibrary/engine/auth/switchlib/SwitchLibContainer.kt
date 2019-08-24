package kr.saintdev.switchlibrary.engine.auth.switchlib

import com.google.gson.JsonObject

data class SwitchLibAuthContainer(
    val status: String?,
    val body: JsonObject?
)