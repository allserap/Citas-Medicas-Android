package com.citas.medicas.models

import com.google.gson.annotations.SerializedName

data class RegistroResponse (
    @SerializedName("success") val success: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: UsuarioData? = null
)

data class UsuarioData(
    val id: Int,
    val email: String,
    val nombre: String
)