package com.citas.medicas.ui.medico

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.citas.medicas.R
import com.citas.medicas.databinding.FragmentEstadoBinding

class EstadoFragment : Fragment(R.layout.fragment_estado) {
    // Inicializar el binding
    private var _binding: FragmentEstadoBinding? = null

    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentEstadoBinding.bind(view)

        setupListeners()
    }
    private fun setupListeners() {
        with(binding) {
            cardDisponible.setOnClickListener {
                actualizarUIEstado(isDisponible = true)
            }
            cardEmergencia.setOnClickListener {
                actualizarUIEstado(isDisponible = false)
            }
        }
    }

    private fun actualizarUIEstado(isDisponible: Boolean) {
        with(binding) {
            if (isDisponible) {
                cardDisponible.strokeWidth = 4
                cardEmergencia.strokeWidth = 0
                Toast.makeText(requireContext(), "Estado: Disponible", Toast.LENGTH_SHORT).show()
            } else {
                cardEmergencia.strokeWidth = 4
                cardDisponible.strokeWidth = 0
                Toast.makeText(requireContext(), "Estado: En Emergencia", Toast.LENGTH_SHORT).show()
            }
        }
    }
}