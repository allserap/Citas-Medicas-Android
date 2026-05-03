package com.citas.medicas.ui.paciente

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.citas.medicas.R
import com.google.android.material.button.MaterialButton

class EditarPerfilActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_editar_perfil)


        val btnAtras = findViewById<TextView>(R.id.tvBackEdit)
        val btnGuardar = findViewById<MaterialButton>(R.id.btnGuardarPerfil)

        btnAtras.setOnClickListener {
            finish()
        }

        btnGuardar.setOnClickListener {

            Toast.makeText(this, "Perfil actualizado correctamente", Toast.LENGTH_SHORT).show()

            finish()
        }

    }
}