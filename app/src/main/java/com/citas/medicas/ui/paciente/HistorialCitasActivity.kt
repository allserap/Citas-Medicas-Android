package com.citas.medicas.ui.paciente

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.citas.medicas.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout

class HistorialCitasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_historial_citas)


        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigationHistorial)


//
//        para ver datos de forma quemada, la ali me obligo
        val tabLayout = findViewById<TabLayout>(R.id.tabLayoutHistorial)
        val proximas = findViewById<LinearLayout>(R.id.llProximasContainer)
        val pasadas = findViewById<LinearLayout>(R.id.llPasadasContainer)
        val btnNuevaCita = findViewById<Button>(R.id.btnNuevaCitaFlotante)


        btnNuevaCita.setOnClickListener {
            startActivity(Intent(this, SolicitarCitaActivity::class.java))
        }

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> { // Próximas
                        proximas.visibility = View.VISIBLE
                        pasadas.visibility = View.GONE
                    }
                    1 -> { // Pasadas
                        proximas.visibility = View.GONE
                        pasadas.visibility = View.VISIBLE
                    }
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })


        bottomNav.selectedItemId = R.id.nav_historial

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_inicio -> {
                    val intent = Intent(this, HomePacienteActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                    startActivity(intent)
                    true
                }
                R.id.nav_solicitar -> {
                    val intent = Intent(this, SolicitarCitaActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                    startActivity(intent)
                    true
                }
                R.id.nav_historial -> {
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