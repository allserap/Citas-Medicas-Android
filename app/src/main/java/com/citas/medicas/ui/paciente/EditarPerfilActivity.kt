package com.citas.medicas.ui.paciente

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.citas.medicas.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class EditarPerfilActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_editar_perfil)


        val btnAtras = findViewById<TextView>(R.id.tvBackEdit)
        val btnGuardar = findViewById<MaterialButton>(R.id.btnGuardarPerfil)


        val etDireccion = findViewById<TextInputEditText>(R.id.etEditDireccion)
        val etTelefono = findViewById<TextInputEditText>(R.id.etEditTelefono)
        val etAlergias = findViewById<TextInputEditText>(R.id.etEditAlergias)
        val etCronicas = findViewById<TextInputEditText>(R.id.etEditCronicas)

        val telefonoRecibido = intent.getStringExtra("EXTRA_TELEFONO")
        val alergiasRecibidas = intent.getStringExtra("EXTRA_ALERGIAS")
        val cronicasRecibidas = intent.getStringExtra("EXTRA_CRONICAS")
        val direccionRecibida = intent.getStringExtra("EXTRA_DIRECCION")

        etTelefono.setText(telefonoRecibido)
        etAlergias.setText(alergiasRecibidas)
        etCronicas.setText(cronicasRecibidas)
        etDireccion.setText(direccionRecibida) // Este estará vacío por ahora

        btnAtras.setOnClickListener {
            finish()
        }

        btnGuardar.setOnClickListener {

            Toast.makeText(this, "Perfil actualizado correctamente", Toast.LENGTH_SHORT).show()

            finish()
        }

    }
}