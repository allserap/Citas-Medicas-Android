package com.citas.medicas.data

import com.citas.medicas.models.ApiResponseEspecialidades
import com.citas.medicas.models.ApiResponseHistorial
import com.citas.medicas.models.ApiResponseHorarios
import com.citas.medicas.models.ApiResponseMapa
import com.citas.medicas.models.ApiResponsePerfil
import com.citas.medicas.models.ApiResponseProximasCitas
import com.citas.medicas.models.ApiResponseUnidades
import com.citas.medicas.models.LoginRequest
import com.citas.medicas.models.LoginResponse
import com.citas.medicas.models.RegistroRequest
import com.citas.medicas.models.RegistroResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @POST("auth/login")
    suspend fun loginUsuario(@Body request: LoginRequest) : Response<LoginResponse>

    @POST("auth/register/paciente")
    suspend fun registrarPaciente(@Body request: RegistroRequest): Response<RegistroResponse>

    @POST("auth/register/medico")
    suspend fun registrarMedico(@Body request: RegistroRequest): Response<LoginResponse>

    @GET("api/citas/proximas/{pacienteId}")
    suspend fun getProximasCitas(
        @Path("pacienteId") pacienteId: Int
    ): Response<ApiResponseProximasCitas>

    @GET("api/citas/historial/{pacienteId}")
    suspend fun getHistorialCitas(
        @Path("pacienteId") pacienteId: Int
    ): Response<ApiResponseHistorial>

    @GET("api/citas/perfil/{pacienteId}")
    suspend fun getPerfilPaciente(
        @Path("pacienteId") pacienteId: Int
    ): Response<ApiResponsePerfil>

    @GET("api/citas/unidades-mapa")
    suspend fun getUnidadesMapa(): Response<ApiResponseMapa>

    // Paso 1
    @GET("api/citas/especialidades")
    suspend fun getEspecialidades(): Response<ApiResponseEspecialidades>

    @GET("api/citas/unidades-medicas/{idEspecialidad}")
    suspend fun getUnidadesFiltradas(
        @Path("idEspecialidad") idEspecialidad: Int
    ): Response<ApiResponseUnidades>

    @GET("api/citas/horarios-disponibles")
    suspend fun getHorariosDisponibles(
        @Query("unidadId") unidadId: Int,
        @Query("especialidadId") especialidadId: Int,
        @Query("fecha") fecha: String
    ): Response<ApiResponseHorarios>
}