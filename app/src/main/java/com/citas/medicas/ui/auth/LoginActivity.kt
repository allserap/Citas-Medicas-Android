package com.citas.medicas.ui.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.citas.medicas.data.RetrofitClient
import com.citas.medicas.databinding.ActivityLoginBinding
import com.citas.medicas.models.LoginRequest
import com.citas.medicas.ui.admin.DashboardAdminActivity
import com.citas.medicas.ui.medico.DashboardMedicoActivity
import com.citas.medicas.ui.paciente.HomePacienteActivity
import com.citas.medicas.utils.RolesUsuario
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private var idRol: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //inicializar binding
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Recuperar rol del splash
        idRol = intent.getIntExtra("rol", RolesUsuario.PACIENTE)

        interfazPorRol()
        setupListeners()

    }

    private fun setupListeners() {
        binding.tvIrARegistro.setOnClickListener {
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.etAfiliado.text.toString()
            val pass = binding.etClave.text.toString()

            if (email.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show()
            }else{
                ejecutarLogin(email, pass)
            }
        }
    }

    //Login segun rol
    private fun interfazPorRol(){
        when(idRol){
            RolesUsuario.PACIENTE -> binding.tvIdentificador.text = "Número de Afiliado"
            RolesUsuario.MEDICO -> binding.tvIdentificador.text = "JVPM"
            else -> binding.tvIdentificador.text = "Identificador"
        }
    }

    private fun ejecutarLogin(usuario: String, clave: String) {
        //temporal
        when {
            usuario == "admin@citas.com" && clave == "admin123" -> {
                persistirYNAvegar("admin_01", "Administrador", RolesUsuario.ADMIN, DashboardAdminActivity::class.java)
                return
            }
            usuario == "medico@citas.com" && clave == "medico123" -> {
                persistirYNAvegar("med_01", "Roberto Flores", RolesUsuario.MEDICO, DashboardMedicoActivity::class.java)
                return
            }
        }

        val request = LoginRequest(usuario, clave, idRol)

        lifecycleScope.launch {
            try {
                val response = RetrofitClient.apiService.loginUsuario(request)

                if (response.isSuccessful && response.body()?.success == true) {
                    val loginResponse = response.body()!!
                    val user = loginResponse.data

                    // --- PERSISTENCIA DE DATOS ---
                    val prefs = getSharedPreferences("CitasMedicasPrefs", MODE_PRIVATE)
                    with(prefs.edit()) {
                        putString("user_id", user?.id)
                        putString("user_nombre", user?.nombre)
                        putInt("user_rol", idRol)
                        apply()
                    }

                    val nombreUsuario = user?.nombre ?: "Usuario"
                        when (idRol) {
                            RolesUsuario.PACIENTE -> navegarA(HomePacienteActivity::class.java, "Bienvenido $nombreUsuario")
                           //RolesUsuario.MEDICO -> navegarA(DashboardMedicoActivity::class.java, "Bienvenido Dr. $nombreUsuario")
                            //RolesUsuario.ADMIN -> navegarA(DashboardAdminActivity::class.java, "Panel Admin")
                        }
                    finish()
                    } else {
                    val errorMsg = response.errorBody()?.string() ?: "Credenciales incorrectas"
                    Toast.makeText(this@LoginActivity, errorMsg, Toast.LENGTH_SHORT).show()
                }


            } catch (e: Exception) {
                Log.e("LOGIN_ERROR", "Fallo: ${e.message}")
                Toast.makeText(this@LoginActivity, "Error de red: Verifique su conexión", Toast.LENGTH_SHORT).show()
            }
        }
    }

    //temporal
    private fun persistirYNAvegar(id: String, nombre: String, rol: Int, destino: Class<*>) {
        // Persistencia
        val prefs = getSharedPreferences("CitasMedicasPrefs", MODE_PRIVATE)
        with(prefs.edit()) {
            putString("user_id", id)
            putString("user_nombre", nombre)
            putInt("user_rol", rol)
            apply()
        }

        // Navegación
        val mensaje = if (rol == RolesUsuario.MEDICO) "Bienvenido Dr. $nombre" else "Bienvenido $nombre"
        navegarA(destino, mensaje)
        finish()
    }

    // Función auxiliar
    private fun navegarA(destino: Class<*>, mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
        val intent = Intent(this, destino).apply {
            // "matar" el Login para que no se pueda volver atrás
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        startActivity(intent)
    }

}