package com.citas.medicas.models

data class Usuario (
    val nombre: String,
    val especialidad: String,
    val rol: String,
    var estaActivo: Boolean = true
)

