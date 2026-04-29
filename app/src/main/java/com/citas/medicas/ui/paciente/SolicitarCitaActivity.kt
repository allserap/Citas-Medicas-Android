package com.citas.medicas.ui.paciente

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.citas.medicas.R
import com.google.android.material.button.MaterialButton

class SolicitarCitaActivity : AppCompatActivity() {
    private var pasoActual = 1

    // Variables para saber si ya se seleccionó algo en cada paso
    private var haSeleccionadoEspecialidad = false
    private var haSeleccionadoUnidad = false
    private var haSeleccionadoFechaHora = false

    // Datos de prueba
    private var especialidadSeleccionada = "Cardiología"
    private var unidadSeleccionada = "Hospital Zacamil"
    private var medicoSeleccionado = "Dra. María González"
    private var fechaSeleccionada = "20/05/2026"
    private var horaSeleccionada = "02:00 PM"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_solicitar_cita)

        val btnSiguiente = findViewById<MaterialButton>(R.id.btnSiguiente)
        val btnAnterior = findViewById<MaterialButton>(R.id.btnAnterior)

        // Referencias a los contenedores  para usarlos como botones de prueba
        val layoutStep1 = findViewById<LinearLayout>(R.id.layoutStep1)
        val layoutStep2 = findViewById<LinearLayout>(R.id.layoutStep2)
        val tvSelectDate = findViewById<TextView>(R.id.tvSelectDate)

        actualizarVista()

        // SIMULACIÓN DE VALIDACIONES

        // Al tocar cualquier parte del área blanca del Paso 1
        layoutStep1.setOnClickListener {
            haSeleccionadoEspecialidad = true
            btnSiguiente.isEnabled = true
        }

        // Al tocar cualquier parte del área blanca del Paso 2
        layoutStep2.setOnClickListener {
            haSeleccionadoUnidad = true
            btnSiguiente.isEnabled = true
        }

        // Al seleccionar la fecha (Paso 3)
        tvSelectDate.setOnClickListener {
            tvSelectDate.text = fechaSeleccionada
            haSeleccionadoFechaHora = true
            btnSiguiente.isEnabled = true
        }

        btnSiguiente.setOnClickListener {
            if (pasoActual < 4) {
                pasoActual++
                actualizarVista()
            } else {
                val intent = Intent(this, HomePacienteActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
            }
        }

        btnAnterior.setOnClickListener {
            if (pasoActual > 1) {
                pasoActual--
                actualizarVista()
            } else {
                finish()
            }
        }
    }

    private fun actualizarVista() {
        val layoutStep1 = findViewById<LinearLayout>(R.id.layoutStep1)
        val layoutStep2 = findViewById<LinearLayout>(R.id.layoutStep2)
        val layoutStep3 = findViewById<LinearLayout>(R.id.layoutStep3)
        val layoutSuccess = findViewById<LinearLayout>(R.id.layoutSuccess)
        val btnSiguiente = findViewById<MaterialButton>(R.id.btnSiguiente)
        val btnAnterior = findViewById<MaterialButton>(R.id.btnAnterior)
        val tvStepCounter = findViewById<TextView>(R.id.tvStepCounter)

        // Ocultar todo
        layoutStep1.visibility = View.GONE
        layoutStep2.visibility = View.GONE
        layoutStep3.visibility = View.GONE
        layoutSuccess.visibility = View.GONE

        when (pasoActual) {
            1 -> {
                layoutStep1.visibility = View.VISIBLE
                btnAnterior.visibility = View.VISIBLE
                tvStepCounter.text = "Paso 1 de 3"
                btnSiguiente.text = "Siguiente"

                //  Si no ha seleccionado, el botón se apaga
                btnSiguiente.isEnabled = haSeleccionadoEspecialidad
            }
            2 -> {
                layoutStep2.visibility = View.VISIBLE
                btnAnterior.visibility = View.VISIBLE
                tvStepCounter.text = "Paso 2 de 3"
                btnSiguiente.text = "Siguiente"

                btnSiguiente.isEnabled = haSeleccionadoUnidad
            }
            3 -> {
                layoutStep3.visibility = View.VISIBLE
                btnAnterior.visibility = View.VISIBLE
                tvStepCounter.text = "Paso 3 de 3"
                btnSiguiente.text = "Confirmar Cita"

                // VALIDACIÓN
                btnSiguiente.isEnabled = haSeleccionadoFechaHora
            }
            4 -> {
                layoutSuccess.visibility = View.VISIBLE
                btnAnterior.visibility = View.GONE
                btnSiguiente.text = "Volver al Inicio"
                btnSiguiente.isEnabled = true // El botón de volver siempre activo
                tvStepCounter.text = "¡Cita Confirmada!"

                findViewById<TextView>(R.id.tvResumenEspecialidad).text = "Especialidad: $especialidadSeleccionada \nUnidad: $unidadSeleccionada"
                findViewById<TextView>(R.id.tvResumenMedico).text = "Médico: $medicoSeleccionado"
                findViewById<TextView>(R.id.tvResumenFecha).text = "Fecha: $fechaSeleccionada"
                findViewById<TextView>(R.id.tvResumenHora).text = "Hora: $horaSeleccionada"
            }
        }
    }
}