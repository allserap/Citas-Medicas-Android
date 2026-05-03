package com.citas.medicas.ui.medico

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.citas.medicas.R
import com.citas.medicas.databinding.FragmentRecetasBinding


class RecetasFragment : Fragment(R.layout.fragment_recetas) {
    // Inicializar el binding
    private var _binding: FragmentRecetasBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentRecetasBinding.bind(view)

        setupListeners()
    }

    private fun setupListeners() {
        binding.btnGenerarReceta.setOnClickListener {
            if (validarReceta()) {
                Toast.makeText(requireContext(), "Receta generada con éxito", Toast.LENGTH_SHORT).show()
            }
        }
        binding.btnAgregarMedicamento.setOnClickListener {
            agregarCampoMedicamento()
        }
    }

    private fun validarReceta(): Boolean {
        with(binding) {
            var isValid = true

            // Validar que el contenedor de medicamentos no esté vacío
            if (containerMedicamentos.childCount == 0) {
                Toast.makeText(requireContext(), "Debe agregar al menos un medicamento", Toast.LENGTH_SHORT).show()
                isValid = false
            }

            return isValid
        }
    }

    private fun agregarCampoMedicamento() {
        val viewMed = layoutInflater.inflate(R.layout.item_medicamento, binding.containerMedicamentos, false)
        binding.containerMedicamentos.addView(viewMed)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}