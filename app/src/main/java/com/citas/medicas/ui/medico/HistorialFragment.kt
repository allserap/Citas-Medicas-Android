package com.citas.medicas.ui.medico

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.citas.medicas.R
import com.citas.medicas.data.RetrofitClient
import com.citas.medicas.databinding.FragmentHistorialBinding
import com.citas.medicas.models.HistorialRequest
import kotlinx.coroutines.launch

class HistorialFragment : Fragment(R.layout.fragment_historial) {
    // Inicializar el binding
    private var _binding: FragmentHistorialBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHistorialBinding.bind(view)

        val idPaciente = arguments?.getString("paciente")
        if (idPaciente != null) {
            cargarHistorial(idPaciente)
        }

        setupSpinner()
    }

    private fun cargarHistorial(idPaciente: String) {
        lifecycleScope.launch {
            try {
                //val response = RetrofitClient.apiService.getHistorial(id)
                //if (response.isSuccessful && response.body() != null) {
                    //mostrarDatos(response.body()!!)
                //} else {
                    // SI NO EXISTE (Caso paciente nuevo)
                  //  mostrarAlertaCreacion(id)
                //}
            } catch (e: Exception) {
                Log.e("API", "Error: ${e.message}")
            }
        }
    }

    private fun mostrarAlertaCreacion(id: String) {
        AlertDialog.Builder(requireContext())
            .setTitle("Paciente sin historial")
            .setMessage("Este paciente no tiene un historial clínico creado. ¿Desea crearlo ahora?")
            .setPositiveButton("Crear") { _, _ ->
                abrirFormularioEdicion(id, esNuevo = true)
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    private fun mostrarDatos(historial: HistorialRequest) {
        with(binding) {
            tvTipoSangre.text = historial.tipoSangre ?: "No definido"
            tvAlergias.text = historial.alergias ?: "Sin alergias"
            tvCondiciones.text = historial.condicionesCronicas ?: "Ninguna"
            tvMedicamentos.text = historial.medicamentosRecurrente ?: "Ninguno"
            tvNotasClinicas.text = historial.notaClinica ?: ""

            // Mostrar botón de editar
            btnEditarHistorial.visibility = View.VISIBLE
            btnEditarHistorial.text = "Editar Historial"
            btnEditarHistorial.setOnClickListener {
                abrirFormularioEdicion(historial.usuario, esNuevo = false)
            }
        }
    }

    private fun abrirFormularioEdicion(id: String, esNuevo: Boolean) {

    }

    private fun setupSpinner() {

    }
}
