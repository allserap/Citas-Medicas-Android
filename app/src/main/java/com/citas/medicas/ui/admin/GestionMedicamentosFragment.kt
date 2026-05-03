package com.citas.medicas.ui.admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.citas.medicas.R
import com.citas.medicas.databinding.FragmentGestionMedicamentosBinding

class GestionMedicamentosFragment : Fragment(R.layout.fragment_gestion_medicamentos) {
    // Inicializar el binding
    private var _binding: FragmentGestionMedicamentosBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentGestionMedicamentosBinding.bind(view)
    }

}