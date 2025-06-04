package com.pepinho.freetogame.ui.juegos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pepinho.pmdm.exames.databinding.CafeItemBinding
import com.pepinho.pmdm.exames.model.Cafe
import com.pepinho.pmdm.exames.model.setImageFromBytes


class CafesListAdapter(
    private val onItemClickListener: (Int) -> Unit
) : ListAdapter<Cafe, CafesListAdapter.CafeViewHolder>(CafeDiffCallback) {

    class CafeViewHolder(
        private val binding: CafeItemBinding,
        private val onItemClickListener: (Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(cafe: Cafe) {
            cafe?.let {
                binding.tvNombreCafe.text = it.nombre
                binding.tvTipoCafe.text = it.tipo
                binding.cafeImageView.setImageFromBytes(it.foto)
                binding.root.setOnClickListener {
                    onItemClickListener(cafe.idCafe)
                }
            }
        }
    }

    // La clase CafeDiffCallback implementa la interfaz DiffUtil.ItemCallback<Cafe>
    // para comparar dos objetos de la clase Cafe y determinar si son iguales.
    // Permite al RecyclerView saber si un item ha cambiado, se ha eliminado o se ha añadido.
    object CafeDiffCallback : DiffUtil.ItemCallback<Cafe>() {
        override fun areItemsTheSame(oldItem: Cafe, newItem: Cafe): Boolean {
            // POdríamos comparar por abreviartura, pero es mejor comparar por idJuego
            return oldItem.idCafe == newItem.idCafe
        }

        override fun areContentsTheSame(oldItem: Cafe, newItem: Cafe): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CafeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CafeItemBinding.inflate(inflater, parent, false)
        return CafeViewHolder(binding, onItemClickListener)
    }

    override fun onBindViewHolder(holder: CafeViewHolder, position: Int) {
        // getItem(position) es un método de ListAdapter. Como ya no recogemos la lista completa.
        // getItem(position) nos devuelve el objeto Cafe en la posición indicada.
        val equipo = getItem(position)
        holder.bind(equipo)
    }

    override fun onCurrentListChanged(previousList: MutableList<Cafe>, currentList: MutableList<Cafe>) {
        super.onCurrentListChanged(previousList, currentList)
        // Aquí puedes añadir lógica adicional si es necesario
//        currentList.sortBy { it.nombre } // Sólo si la lista es mutable.
    }

}