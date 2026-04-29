package com.citas.medicas.models

import com.google.gson.annotations.SerializedName

//objt enviado
data class LoginRequest(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("rol")
    val rol: Int
)

