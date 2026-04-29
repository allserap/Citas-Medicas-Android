package com.citas.medicas.ui.admin

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.citas.medicas.R
import com.citas.medicas.databinding.FragmentUsuariosBinding
import com.citas.medicas.models.Usuario

class UsuariosFragment : Fragment(R.layout.fragment_usuarios) {

    private var _binding: FragmentUsuariosBinding? = null
    private val binding get() = _binding!!

    private var isFormVisible = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentUsuariosBinding.bind(view)

        setupSpinner()
        setupListeners()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        // Datos de prueba
        val listaUsuarios = listOf(
            Usuario("Dr. Roberto Flores", "Medicina General", "Médico"),
            Usuario("Lic. Carmen López", "Enfermería General", "Enfermería"),
            Usuario("Dr. Pedro Martínez", "Pediatría", "Médico", false)
        )

        val adapter = UsuariosAdapter(listaUsuarios)
        binding.rvUsuarios.apply {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(requireContext())
            this.adapter = adapter
        }
    }

    private fun setupSpinner() {
        // Configurar las opciones del Spinner de Roles
        val roles = listOf("Seleccionar Rol", "Médico", "Enfermería")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, roles)
        binding.spRol.adapter = adapter
    }

    private fun setupListeners() {
        // Botón superior: Nuevo / Cancelar
        binding.btnNuevoUsuario.setOnClickListener {
            toggleFormulario()
        }

        // Botón Crear Usuario
        binding.btnCrear.setOnClickListener {
            validarYCrear()
        }
    }

    private fun toggleFormulario() {
        isFormVisible = !isFormVisible

        if (isFormVisible) {
            // Mostrar formulario
            binding.cardFormulario.visibility = View.VISIBLE
            binding.cardFormulario.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.citas_white))
            binding.btnNuevoUsuario.text = "Cancelar"
            binding.btnNuevoUsuario.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.citas_secondary))
            binding.btnCrear.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.citas_secondary))
        } else {
            // Ocultar formulario
            binding.cardFormulario.visibility = View.GONE
            binding.btnNuevoUsuario.text = "Nuevo"
            binding.btnNuevoUsuario.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.citas_secondary))
            limpiarCampos()
        }
    }

    private fun limpiarCampos() {
        binding.etNombre.text?.clear()
        binding.etCorreo.text?.clear()
        binding.etEspecialidad.text?.clear()
        binding.spRol.setSelection(0)
    }

    private fun validarYCrear() {
        val nombre = binding.etNombre.text.toString()
        if (nombre.isEmpty()) {
            binding.etNombre.error = "Campo requerido"
            return
        }else{
            Toast.makeText(requireContext(), "Usuario creado con éxito", Toast.LENGTH_SHORT).show()
        }
        // Cerrar el formulario al crear
        toggleFormulario()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}