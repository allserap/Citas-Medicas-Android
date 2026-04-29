package com.citas.medicas.ui.admin

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.citas.medicas.R
import com.citas.medicas.databinding.FragmentHorariosBinding
import com.citas.medicas.databinding.ItemHorarioDiaBinding

class HorariosFragment : Fragment(R.layout.fragment_horarios) {

    private var _binding: FragmentHorariosBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHorariosBinding.bind(view)

        initUI()
    }

    private fun initUI() {
        // Configurar textos de los días
        setupDia(binding.itemLunes, "Lunes")
        setupDia(binding.itemMartes, "Martes")
        setupDia(binding.itemMiercoles, "Miércoles")
        setupDia(binding.itemJueves, "Jueves")
        setupDia(binding.itemViernes, "Viernes")
        setupDia(binding.itemSabado, "Sábado")

        // Guardar
        binding.btnGuardarHorario.setOnClickListener {
            guardarConfiguracion()
        }
    }

    private fun setupDia(itemBinding: ItemHorarioDiaBinding, nombreDia: String) {
        itemBinding.tvNombreDia.text = nombreDia

        // Visual cuando se apaga el switch
        itemBinding.switchDia.setOnCheckedChangeListener { _, isChecked ->
            val alpha = if (isChecked) 1.0f else 0.4f
            itemBinding.tvHoraInicio.isEnabled = isChecked
            itemBinding.tvHoraFin.isEnabled = isChecked
            itemBinding.root.alpha = alpha
        }

        itemBinding.tvHoraInicio.setOnClickListener { /* Abrir Reloj */ }
        itemBinding.tvHoraFin.setOnClickListener { /* Abrir Reloj */ }
    }

    private fun guardarConfiguracion() {
        // Leer los datos
        val lunesActivo = binding.itemLunes.switchDia.isChecked
        val horaLunes = binding.itemLunes.tvHoraInicio.text.toString()

        Toast.makeText(context, "Configuración guardada exitosamente", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}