package com.pepinho.freetogame.ui.juegos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.pepinho.freetogame.R
import com.pepinho.freetogame.databinding.ItemJuegoBinding
import com.pepinho.freetogame.model.Juego
import com.pepinho.freetogame.model.Plataforma

class JuegosListAdapter(
    private val onItemClickListener: (Int) -> Unit
) : ListAdapter<Juego, JuegosListAdapter.JuegoViewHolder>(JuegoDiffCallback) {

    class JuegoViewHolder(
        private val binding: ItemJuegoBinding,
        private val onItemClickListener: (Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(juego: Juego) {
            juego?.let {
                binding.tvTitulo.text = it.titulo
                binding.tvUrl.text = it.url
                binding.miniaturaImageView.load(it.miniatura) {
                    placeholder(R.drawable.ic_launcher_foreground) // Imagen de placeholder
                    error(R.drawable.game) // Imagen en caso de error
                }
                binding.root.setOnClickListener {
                    onItemClickListener(juego.idJuego)
                }
            }
        }
    }

    // La clase JuegoDiffCallback implementa la interfaz DiffUtil.ItemCallback<Juego>
    // para comparar dos objetos de la clase Juego y determinar si son iguales.
    // Permite al RecyclerView saber si un item ha cambiado, se ha eliminado o se ha añadido.
    object JuegoDiffCallback : DiffUtil.ItemCallback<Juego>() {
        override fun areItemsTheSame(oldItem: Juego, newItem: Juego): Boolean {
            // POdríamos comparar por abreviartura, pero es mejor comparar por idJuego
            return oldItem.idJuego == newItem.idJuego
        }

        override fun areContentsTheSame(oldItem: Juego, newItem: Juego): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JuegoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemJuegoBinding.inflate(inflater, parent, false)
        return JuegoViewHolder(binding, onItemClickListener)
    }

    override fun onBindViewHolder(holder: JuegoViewHolder, position: Int) {
        // getItem(position) es un método de ListAdapter. Como ya no recogemos la lista completa.
        // getItem(position) nos devuelve el objeto Juego en la posición indicada.
        val equipo = getItem(position)
        holder.bind(equipo)
    }

    override fun onCurrentListChanged(previousList: MutableList<Juego>, currentList: MutableList<Juego>) {
        super.onCurrentListChanged(previousList, currentList)
        // Aquí puedes añadir lógica adicional si es necesario
//        currentList.sortBy { it.nombre } // Sólo si la lista es mutable.
    }

}