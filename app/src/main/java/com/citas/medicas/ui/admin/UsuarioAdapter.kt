package com.citas.medicas.ui.admin

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.citas.medicas.R
import com.citas.medicas.databinding.ItemUsuarioBinding
import com.citas.medicas.models.Usuario

class UsuariosAdapter(private var usuarios: List<Usuario>) :
    RecyclerView.Adapter<UsuariosAdapter.UsuarioViewHolder>() {

    inner class UsuarioViewHolder(val binding: ItemUsuarioBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuarioViewHolder {
        val binding = ItemUsuarioBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return UsuarioViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UsuarioViewHolder, position: Int) {
        val usuario = usuarios[position]
        val context = holder.itemView.context

        with(holder.binding) {
            // Asignar textos
            tvNombre.text = usuario.nombre
            tvEspecialidad.text = usuario.especialidad
            tvRolBadge.text = usuario.rol

            // Tomar las primeras letras
            val palabras = usuario.nombre.split(" ")
            val iniciales = palabras.filter { it.length > 2 }
                .take(2)
                .joinToString("") { it.take(1).uppercase() }
            tvIniciales.text = iniciales

            // Estilo según el Rol
            if (usuario.rol.lowercase() == "enfermería") {
                tvRolBadge.backgroundTintList = ContextCompat.getColorStateList(context, R.color.citas_secondary)
                tvRolBadge.setTextColor(ContextCompat.getColor(context, R.color.citas_primary))
                tvIniciales.backgroundTintList = ContextCompat.getColorStateList(context, R.color.citas_background)
            } else {
                tvRolBadge.backgroundTintList = ContextCompat.getColorStateList(context, R.color.citas_primary)
                tvRolBadge.setTextColor(ContextCompat.getColor(context, R.color.citas_white))
            }

            // Manejo del Switch y Estado Visual
            switchEstado.setOnCheckedChangeListener(null)
            switchEstado.isChecked = usuario.estaActivo
            actualizarInterfazEstado(holder, usuario.estaActivo)

            switchEstado.setOnCheckedChangeListener { _, isChecked ->
                usuario.estaActivo = isChecked
                actualizarInterfazEstado(holder, isChecked)
            }
        }
    }

    private fun actualizarInterfazEstado(holder: UsuarioViewHolder, activo: Boolean) {
        with(holder.binding) {
            if (activo) {
                tvEstadoTexto.text = "Activo"
                root.alpha = 1.0f
                tvEstadoTexto.setTextColor(ContextCompat.getColor(root.context, android.R.color.darker_gray))
            } else {
                tvEstadoTexto.text = "Suspendido"
                root.alpha = 0.6f // Efecto de deshabilitado
                tvEstadoTexto.setTextColor(ContextCompat.getColor(root.context, android.R.color.holo_red_dark))
            }
        }
    }

    override fun getItemCount() = usuarios.size

    fun updateList(newList: List<Usuario>) {
        this.usuarios = newList
        notifyDataSetChanged()
    }
}