package com.citas.medicas.ui.paciente

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.citas.medicas.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.card.MaterialCardView

class HomePacienteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home_paciente_activityn)



        val btnSolicitar = findViewById<MaterialCardView>(R.id.cvActionSolicitar)
        val btnMisCitas = findViewById<MaterialCardView>(R.id.cvActionMisCitas)
        val btnUnidades = findViewById<MaterialCardView>(R.id.cvActionUnidades)
        val btnPerfil = findViewById<MaterialCardView>(R.id.cvActionPerfil)
        val tvVerTodasCitas = findViewById<TextView>(R.id.tvVerTodasCitas)


        tvVerTodasCitas.setOnClickListener {
            startActivity(Intent(this, HistorialCitasActivity::class.java))
        }

        btnSolicitar.setOnClickListener {
            startActivity(Intent(this, SolicitarCitaActivity::class.java))
        }

        btnMisCitas.setOnClickListener {
            startActivity(Intent(this, HistorialCitasActivity::class.java))
        }

        btnUnidades.setOnClickListener {
            startActivity(Intent(this, MapaActivity::class.java))
        }

        btnPerfil.setOnClickListener {
            startActivity(Intent(this, PerfilActivity::class.java))
        }

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigation)

        bottomNav.selectedItemId = R.id.nav_inicio

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_inicio -> {
                    true
                }
                R.id.nav_solicitar -> {
                    val intent = Intent(this, SolicitarCitaActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                    startActivity(intent)
                    true
                }

                R.id.nav_historial -> {
                    val intent = Intent(this, HistorialCitasActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                    startActivity(intent)
                    true
                }
                R.id.nav_mapa -> {
                    val intent = Intent(this, MapaActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                    startActivity(intent)
                    true
                }
                R.id.nav_perfil -> {
                    val intent = Intent(this, PerfilActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                    startActivity(intent)
                    true
                }

                else -> false
            }
        }

    }
}