package com.citas.medicas.models
import com.google.gson.annotations.SerializedName

//objt a recibir
data class LoginResponse(
    @SerializedName("succes") val success: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: UserProfile? = null,
    @SerializedName("token") val token: String? = null
)

//info real
data class UserProfile(
    val id: String,
    val nombre: String,
    val apellido: String,
    val email: String,
    @SerializedName("rolId") val rolId: Int,
    // Datos adicionales del SELECT de Node.js
    val numAfiliado: String? = null,
    val numJvpm: String? = null,
    val especialidadId: Int? = null
)