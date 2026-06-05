package com.example.eventmaster.dto
import com.google.gson.annotations.SerializedName

data class LoginResponseDto(
    @SerializedName("token") val token: String,
    @SerializedName("message") val message: String?
)