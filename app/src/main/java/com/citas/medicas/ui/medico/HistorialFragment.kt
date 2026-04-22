package com.citas.medicas.ui.medico

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.citas.medicas.R
import com.citas.medicas.databinding.FragmentHistorialBinding

class HistorialFragment : Fragment(R.layout.fragment_historial) {
    // Inicializar el binding
    private var _binding: FragmentHistorialBinding? = null

    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHistorialBinding.bind(view)

        // Recuperación de datos enviados desde Agenda
        val idPaciente = arguments?.getString("paciente_id")

        setupListeners()
    }

    private fun setupListeners() {

    }

}