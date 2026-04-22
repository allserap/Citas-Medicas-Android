package com.citas.medicas.ui.auth

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.citas.medicas.R
import com.citas.medicas.databinding.ActivityRegistroBinding
import com.citas.medicas.ui.admin.DashboardAdminActivity
import java.util.*

class RegistroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inicializar el binding
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        // Uso de binding.root en lugar de R.layout
        setContentView(binding.root)

        setupListeners()
    }

        private fun setupListeners() {
            // Configuración del DatePicker para la fecha de nacimiento
            binding.etFechaR.setOnClickListener { showDatePickerDialog(binding.etFechaR) }

            // Volver al login
            binding.tvVolverALogin.setOnClickListener {
                startActivity(Intent(this, LoginActivity::class.java))
                finish() // Cerrar registro para no acumular pantallas
            }

            binding.btnCrearCuenta.setOnClickListener {
                if (validarForm()) {
                    Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
                    // Por el momento para visualizar el flujo de pantallas
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }
            }
        }



    // Validar los datos ingrsados por el usuario
    fun validarForm(): Boolean {

        with(binding) {
            val afiliado = etAfiliadoR.text.toString().trim()
            val nombre = etNombreR.text.toString().trim()
            val apellido = etApellidoR.text.toString().trim()
            val dui = etDuiR.text.toString().trim()
            val fecha = etFechaR.text.toString().trim()
            val correo = etCorreoR.text.toString().trim()
            val telefono = etTelefonoR.text.toString().trim()
            val password = etClaveR.text.toString()
            val confirmPassword = etConfirmarClaveR.text.toString()


            var isValid = true

            if (afiliado.length !in 6..10 || !afiliado.all { it.isDigit() }) {
                etAfiliadoR.error = "Número inválido (6-10 dígitos)"
                isValid = false
            }

            if (nombre.isEmpty()) {
                etNombreR.error = "Ingrese su nombre"
                isValid = false
            }

            if (apellido.isEmpty()) {
                etApellidoR.error = "Ingrese su apellido"
                isValid = false
            }

            if (!isValidDUI(dui)) {
                etDuiR.error = "Formato inválido (00000000-0)"
                isValid = false
            }

            if (fecha.isEmpty()) {
                etFechaR.error = "Seleccione su fecha"
                isValid = false
            }

            if (!isValidEmail(correo)) {
                etCorreoR.error = "Correo inválido"
                isValid = false
            }

            if (!isValidPhone(telefono)) {
                etTelefonoR.error = "Formato inválido (0000-0000)"
                isValid = false
            }

            if (!isValidPassword(password)) {
                etClaveR.error = "Mínimo 8 caracteres, 1 mayúscula y 1 símbolo"
                isValid = false
            }

            if (password != confirmPassword) {
                etConfirmarClaveR.error = "Las contraseñas no coinciden"
                isValid = false
            }


            return isValid
        }
    }

    fun isValidPassword(password: String): Boolean {
        val passwordRegex = Regex("^(?=.*[A-Z])(?=.*[!@#\$%^&*(),.?\":{}|<>]).{8,}$")
        return password.matches(passwordRegex)
    }

    fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isValidDUI(dui: String): Boolean {
        val regex = Regex("^\\d{8}-\\d$")
        return dui.matches(regex)
    }

    fun isValidPhone(phone: String): Boolean {
        val regex = Regex("^\\d{4}-\\d{4}$")
        return phone.matches(regex)
    }

    private fun showDatePickerDialog(editText: EditText) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePicker = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
            val date = "$selectedDay/${selectedMonth + 1}/$selectedYear"
            editText.setText(date)
        }, year, month, day)

        datePicker.show()
    }
}