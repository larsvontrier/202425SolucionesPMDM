package com.pepinho.nba

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pepinho.nba.databinding.ItemEquipoBinding
import com.pepinho.nba.model.Equipo

class EquiposAdapter2(
    private val onItemClickListener: (Equipo) -> Unit
) : ListAdapter<Equipo, EquiposAdapter2.EquipoHolder2>(EquipoDiffCallback) {

    class EquipoHolder2(
        private val binding: ItemEquipoBinding,
        private val onItemClickListener: (Equipo) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(equipo: Equipo) {
            equipo?.let {
                binding.tvNombre.text = it.nombre
                binding.tvAbreviatura.text = it.abreviatura

                val resourceId = EquipoIconUtil.getIconResource(it.abreviatura)
                binding.ivEscudo.setImageResource(resourceId)

                binding.root.setOnClickListener {
                    onItemClickListener(equipo)
                }
            }
        }
    }

    // La clase EquipoDiffCallback implementa la interfaz DiffUtil.ItemCallback<Equipo>
    // para comparar dos objetos de la clase Equipo y determinar si son iguales.
    // Permite al RecyclerView saber si un item ha cambiado, se ha eliminado o se ha añadido.
    object EquipoDiffCallback : DiffUtil.ItemCallback<Equipo>() {
        override fun areItemsTheSame(oldItem: Equipo, newItem: Equipo): Boolean {
            // POdríamos comparar por abreviartura, pero es mejor comparar por idEquipo
            return oldItem.idEquipo == newItem.idEquipo
        }

        override fun areContentsTheSame(oldItem: Equipo, newItem: Equipo): Boolean {
            return oldItem == newItem
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EquipoHolder2 {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemEquipoBinding.inflate(inflater, parent, false)
        return EquipoHolder2(binding, onItemClickListener)
    }

    override fun onBindViewHolder(holder: EquipoHolder2, position: Int) {
        val equipo = getItem(position)
        holder.bind(equipo)
    }

}
