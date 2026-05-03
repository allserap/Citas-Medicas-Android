package com.citas.medicas.data

import com.citas.medicas.models.LoginRequest
import com.citas.medicas.models.LoginResponse
import com.citas.medicas.models.RegistroRequest
import com.citas.medicas.models.RegistroResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("auth/login")
    suspend fun loginUsuario(@Body request: LoginRequest) : Response<LoginResponse>

    @POST("auth/register/paciente")
    suspend fun registrarPaciente(@Body request: RegistroRequest): Response<RegistroResponse>

    @POST("auth/register/medico")
    suspend fun registrarMedico(@Body request: RegistroRequest): Response<LoginResponse>
}