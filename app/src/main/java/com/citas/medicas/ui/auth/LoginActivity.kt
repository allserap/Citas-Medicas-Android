package com.citas.medicas.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.citas.medicas.R
import com.citas.medicas.ui.admin.DashboardAdminActivity
import com.citas.medicas.ui.medico.DashboardMedicoActivity
import com.citas.medicas.ui.paciente.HomePacienteActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etAfiliado = findViewById<EditText>(R.id.etAfiliado)
        val etClave = findViewById<EditText>(R.id.etClave)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val tvIrARegistro = findViewById<TextView>(R.id.tvIrARegistro)

        tvIrARegistro.setOnClickListener {
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener {
            val afiliado = etAfiliado.text.toString()
            val pass = etClave.text.toString()

            if (afiliado.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show()
            }
                // Por el momento para visualizar el flujo de pantallas
                validarAcceso(afiliado, pass)
        }
    }
    private fun validarAcceso(user: String, pass: String) {
        when {
            user == "admin" && pass == "1234" -> {
                navegarA(DashboardAdminActivity::class.java, "Bienvenido Administrador")
            }
            user == "medico" && pass == "1234" -> {
                navegarA(DashboardMedicoActivity::class.java, "Bienvenido Dr. Roberto")
            }
            user == "paciente" && pass == "1234" -> {
                navegarA(HomePacienteActivity::class.java, "Bienvenido Paciente")
                Toast.makeText(this, "Dashboard Paciente en desarrollo", Toast.LENGTH_SHORT).show()
            }
            else -> {
                Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Función auxiliar
    private fun navegarA(destino: Class<*>, mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
        val intent = Intent(this, destino)
        startActivity(intent)
        finish()
    }

}