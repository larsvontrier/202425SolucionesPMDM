package com.pepinho.nba.ui.equipos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pepinho.nba.ui.util.EquipoIconUtil
import com.pepinho.nba.databinding.ItemEquipoBinding
import com.pepinho.nba.model.Equipo

class EquiposListAdapter(
    private val onItemClickListener: (Int) -> Unit
) : ListAdapter<Equipo, EquiposListAdapter.EquipoViewHolder>(EquipoDiffCallback) {

    class EquipoViewHolder(
        private val binding: ItemEquipoBinding,
        private val onItemClickListener: (Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(equipo: Equipo) {
            equipo?.let {
                binding.tvNombre.text = it.nombre
                binding.tvAbreviatura.text = it.abreviatura

                val resourceId = EquipoIconUtil.getIconResource(it.abreviatura)
                binding.ivEscudo.setImageResource(resourceId)

                binding.root.setOnClickListener {
                    onItemClickListener(equipo.idEquipo)
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


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EquipoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemEquipoBinding.inflate(inflater, parent, false)
        return EquipoViewHolder(binding, onItemClickListener)
    }

    override fun onBindViewHolder(holder: EquipoViewHolder, position: Int) {
        // getItem(position) es un método de ListAdapter. Como ya no recogemos la lista completa.
        // getItem(position) nos devuelve el objeto Equipo en la posición indicada.
        val equipo = getItem(position)
        holder.bind(equipo)
    }

    override fun onCurrentListChanged(previousList: MutableList<Equipo>, currentList: MutableList<Equipo>) {
        super.onCurrentListChanged(previousList, currentList)
        // Aquí puedes añadir lógica adicional si es necesario
//        currentList.sortBy { it.nombre } // Sólo si la lista es mutable.
    }

}