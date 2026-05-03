package com.citas.medicas.ui.admin

import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.citas.medicas.R
import com.citas.medicas.databinding.FragmentUsuariosBinding
import com.citas.medicas.models.RegistroRequest
import com.citas.medicas.models.Usuario
import com.citas.medicas.ui.auth.AuthViewModel
import com.citas.medicas.utils.RolesUsuario
import java.util.Calendar
import java.util.Locale

class UsuariosFragment : Fragment(R.layout.fragment_usuarios) {

    private var _binding: FragmentUsuariosBinding? = null
    private val binding get() = _binding!!

    private var isFormVisible = false
    private val authViewModel: AuthViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentUsuariosBinding.bind(view)

        setupObservers()
        setupListeners()
        setupRecyclerView()
        configurarSpinners()
    }

    private fun setupRecyclerView() {
        // Datos de prueba
        val listaUsuarios = listOf(
            Usuario("Dr. Roberto Flores", "Medicina General", "Médico"),
            Usuario("Dr. Pedro Martínez", "Pediatría", "Médico", false)
        )

        val adapter = UsuariosAdapter(listaUsuarios)
        binding.rvUsuarios.apply {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(requireContext())
            this.adapter = adapter
        }
    }

    private fun enviarRegistroMedicoAlServidor() {
        //recuperar inicial del género
        val generoSeleccionado = binding.spGenero.selectedItem.toString()
        val generoFinal = when(generoSeleccionado) {
            "Masculino" -> "M"
            "Femenino" -> "F"
            else -> "O"
        }
        // Preparar los datos
        val nuevoUsuario = RegistroRequest(
            nombre = binding.etNombres.text.toString().trim(),
            apellido = binding.etApellidos.text.toString().trim(),
            dui = binding.etDui.text.toString().trim(),
            email = binding.etCorreo.text.toString().trim(),
            password = binding.etPassword.text.toString(),
            telefono = binding.etTelefono.text.toString().trim(),
            fechaNacimiento = binding.etFechaNac.text.toString(),
            numJvpm = binding.etJvpm.text.toString().trim(),
            //datos temporales
            especialidad = 3,
            unidadMedica = 1,
            genero = generoFinal,
            rol = RolesUsuario.MEDICO
        )
        authViewModel.ejecutarRegistro(nuevoUsuario)
    }

    private fun configurarSpinners() {
        val opcionesGenero = arrayOf("Masculino", "Femenino", "Otro")
        configurarSpinnerConHint(binding.spGenero, opcionesGenero, "Seleccione su género")

        val opcionesEspecialidad = arrayOf("Cardiología", "Medicina Interna", "Emergencia", "Otra")
        configurarSpinnerConHint(binding.spnEspecialidad, opcionesEspecialidad, "Seleccione especialidad")
    }

    private fun setupObservers() {
        authViewModel.registroExitoso.observe(viewLifecycleOwner) { mensaje ->
            Toast.makeText(requireContext(), "Médico creado: $mensaje", Toast.LENGTH_SHORT).show()
            toggleFormulario()
        }

        authViewModel.error.observe(viewLifecycleOwner) { errorMsg ->
            Toast.makeText(requireContext(), errorMsg, Toast.LENGTH_LONG).show()
        }
    }

    private fun configurarSpinnerConHint(spinner: Spinner, opciones: Array<String>, hint: String) {
        val listaConHint = arrayOf(hint) + opciones

        val adapter = object : ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item, listaConHint) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = super.getView(position, convertView, parent)
                val tv = view as TextView
                if (position == 0) {
                    tv.setTextColor(Color.GRAY)
                } else {
                    tv.setTextColor(Color.BLACK)
                }
                return view
            }

            override fun isEnabled(position: Int): Boolean {
                return position != 0 // Deshabilita la primera posición
            }

            override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = super.getDropDownView(position, convertView, parent)
                val tv = view as TextView
                if (position == 0) {
                    tv.setTextColor(Color.GRAY)
                } else {
                    tv.setTextColor(Color.BLACK)
                }
                return view
            }
        }

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }
    private fun showDatePickerDialog(editText: EditText) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePicker = DatePickerDialog(requireContext(), { _, selectedYear, selectedMonth, selectedDay ->
            val realMonth = selectedMonth + 1
            val formattedDate = String.format(Locale.US, "%04d-%02d-%02d", selectedYear, realMonth, selectedDay)

            editText.setText(formattedDate)
        }, year, month, day)

        datePicker.show()

        datePicker.show()
    }

    private fun setupListeners() {
        binding.etFechaNac.setOnClickListener { showDatePickerDialog(binding.etFechaNac) }
        // Botón superior: Nuevo / Cancelar
        binding.btnNuevoUsuario.setOnClickListener {
            toggleFormulario()
        }
        // Botón Crear Usuario
        binding.btnCrear.setOnClickListener {
            enviarRegistroMedicoAlServidor()
        }
    }

    //Agregar fun validar form

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

    //falta agregar nuevos campos
    private fun limpiarCampos() {
        binding.etNombres.text?.clear()
        binding.etApellidos.text?.clear()
        binding.etFechaNac.text?.clear()
        binding.etCorreo.text?.clear()
        binding.etTelefono.text.clear()
        binding.spnEspecialidad.setSelection(0)
        binding.etPassword.text?.clear()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}