package com.citas.medicas.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.citas.medicas.data.AuthRepository
import com.citas.medicas.models.RegistroRequest
import kotlinx.coroutines.launch

class AuthViewModel (private val repository: AuthRepository = AuthRepository()) : ViewModel(){
    private val _registroExitoso = MutableLiveData<String?>()
    val registroExitoso: LiveData<String?> get() = _registroExitoso

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun ejecutarRegistro(datos: RegistroRequest) {
        viewModelScope.launch {
            val resultado = if (datos.rol == 2) {
                repository.registrarMedico(datos)
            } else {
                repository.registrarPaciente(datos)
            }
            resultado.onSuccess { mensaje ->
                _registroExitoso.value = mensaje
            }.onFailure { excepcion ->
                _error.value = excepcion.message
            }
        }
    }
}