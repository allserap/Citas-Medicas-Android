package com.citas.medicas.models

import com.google.gson.annotations.SerializedName

data class RegistroRequest(
    val nombre: String,
    val apellido: String,
    val dui: String,
    val genero: String,
    val telefono: String? = null,
    val especialidad: Int? = null, // Solo se enviará si es Médico
    val unidadMedica: Int? = null, // Solo se enviará si es Médico
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("rol")
    val rol: Int,
    @SerializedName("fechaNacimiento")
    val fechaNacimiento: String,
    @SerializedName("estadoFamiliar")
    val estadoFamiliar: String? = null, // Solo se enviará si es Paciente
    @SerializedName("numAfiliado")
    val numAfiliado: String? = null , // Solo se enviará si es Paciente
    @SerializedName("numJvpm")
    val numJvpm: String? = null      // Solo se enviará si es Médico
)