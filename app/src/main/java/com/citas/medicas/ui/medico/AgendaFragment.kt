package com.citas.medicas.ui.medico

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.citas.medicas.R
import com.citas.medicas.databinding.FragmentAgendaBinding

class AgendaFragment : Fragment(R.layout.fragment_agenda) {
    // Inicializar el binding
    private var _binding: FragmentAgendaBinding? = null

    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAgendaBinding.bind(view)

        setupListeners()
    }

    private fun setupListeners() {
        binding.lPaciente.setOnClickListener {
            // Fragment destino
            val fragmentHistorial = HistorialFragment()

            // Enviar datos del paciente
            val bundle = Bundle()
            bundle.putString("nombre_paciente", "María García López")
            bundle.putString("paciente_id", "12345")
            fragmentHistorial.arguments = bundle

            // Navegación mediante Activity
            val dash = (activity as? DashboardMedicoActivity)
            dash?.replaceFragment(fragmentHistorial)

        }
    }


}