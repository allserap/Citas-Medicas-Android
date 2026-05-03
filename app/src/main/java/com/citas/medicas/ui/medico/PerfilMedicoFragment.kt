package com.citas.medicas.ui.medico

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.citas.medicas.R
import com.citas.medicas.databinding.FragmentHistorialBinding
import com.citas.medicas.databinding.FragmentPerfilMedicoBinding

class PerfilMedicoFragment : Fragment(R.layout.fragment_perfil_medico) {
        // Inicializar el binding
        private var _binding: FragmentPerfilMedicoBinding? = null
        private val binding get() = _binding!!

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            _binding = FragmentPerfilMedicoBinding.bind(view)

        }

}
