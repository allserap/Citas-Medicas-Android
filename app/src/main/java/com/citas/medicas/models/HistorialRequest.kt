package com.citas.medicas.models

data class HistorialRequest(
    val usuario: String,
    val tipoSangre: String?,
    val alergias: String?,
    val condicionesCronicas: String?,
    val notaClinica: String?,
    val medicamentosRecurrente: String?
)