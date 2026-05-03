package com.citas.medicas.data

import android.util.Log
import com.citas.medicas.models.RegistroRequest

class AuthRepository {
    suspend fun registrarMedico(request: RegistroRequest): Result<String?> {
        return try {
            // endpoint de médico
            val response = RetrofitClient.apiService.registrarMedico(request)

            if (response.isSuccessful && response.body()?.success == true) {
                Result.success(response.body()?.message)
            } else {
                val errorServidor = response.errorBody()?.string()
                Log.e("API_DEBUG", "Error Médico: $errorServidor")
                Result.failure(Exception(errorServidor ?: "Error en el registro"))
            }
        } catch (e: Exception) {
            Result.failure(Exception("Error de red: ${e.message}"))
        }
    }
    suspend fun registrarPaciente(request: RegistroRequest): Result<String?> {
        return try {
            // llamada a la API
            val response = RetrofitClient.apiService.registrarPaciente(request)

            if (response.isSuccessful && response.body()?.success == true) {
                // registro exitoso
                Result.success(response.body()?.message)
            } else {
                // error como respuesta del servidor
                val errorMsg = response.body()?.message ?: "Error en el registro"
                Result.failure(Exception(errorMsg))
            }
        } catch (e: Exception) {
            // error de red o timeout
            Result.failure(Exception("Error de red: ${e.message}"))
        }
    }
}