package com.pepinho.freetogame.ui.juegos


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.pepinho.freetogame.R
import com.pepinho.freetogame.databinding.ItemJuegoBinding
import com.pepinho.freetogame.model.Juego


class JuegosAdapter(
    private val onItemClickListener: (Int) -> Unit
) : RecyclerView.Adapter<JuegosAdapter.JuegoViewHolder>() {
    private var juegos: List<Juego> = emptyList()

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

    fun updateList(novosJuegos: List<Juego>){
        juegos = novosJuegos
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): JuegoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemJuegoBinding.inflate(inflater, parent, false)
        return JuegoViewHolder(binding, onItemClickListener)
    }

    override fun onBindViewHolder(holder: JuegoViewHolder, position: Int) {
        val juego = juegos[position]
        holder.bind(juego)
    }

    override fun getItemCount() = juegos.size
}