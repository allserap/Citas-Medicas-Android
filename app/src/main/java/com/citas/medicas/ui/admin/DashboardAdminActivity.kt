package com.citas.medicas.ui.admin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.citas.medicas.R
import com.citas.medicas.databinding.ActivityDashboardAdminBinding
import com.citas.medicas.databinding.ActivityDashboardMedicoBinding
import com.citas.medicas.ui.auth.LoginActivity
import com.citas.medicas.ui.auth.RegistroActivity
import com.citas.medicas.ui.medico.AgendaFragment
import com.citas.medicas.ui.medico.HistorialFragment
import com.citas.medicas.ui.medico.RecetasFragment

class DashboardAdminActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardAdminBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard_admin)

        // Inicializar el binding
        binding = ActivityDashboardAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar fragment
        if (savedInstanceState == null) {
            replaceFragment(UsuariosFragment())
            binding.tabUsuarios.setBackgroundResource(R.drawable.bg_tabs)
        }

        setupNavigation()
    }
    private fun setupNavigation() {
        with(binding) {
            tabUsuarios.setOnClickListener {
                replaceFragment(UsuariosFragment())
            }
            tabEstadisticas.setOnClickListener {
                replaceFragment(EstadisticasFragment())
            }
            tabHorarios.setOnClickListener {
                replaceFragment(HorariosFragment())
            }
            tabAuditoria.setOnClickListener {
                replaceFragment(AuditoriaFragment())
            }
            tabRecursos.setOnClickListener {
                replaceFragment(RecursosFragment())
            }

            btnSalir.setOnClickListener {
                Toast.makeText(
                    this@DashboardAdminActivity,
                    "Cerrando sesión...",
                    Toast.LENGTH_SHORT
                ).show()
                val intent = Intent(this@DashboardAdminActivity, LoginActivity::class.java)
                startActivity(intent)
                finishAffinity()
            }
        }
    }

    // Funcion para cambiar de fragment
    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.containerFragment, fragment)
            .commit()
    }
}