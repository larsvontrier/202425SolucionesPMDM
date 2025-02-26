package com.pepinho.pmdm.recyclerviewsimple

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.pepinho.pmdm.recyclerviewsimple.databinding.ItemNombreBinding

class NombresAdapter(val nombres: List<String>) :
    RecyclerView.Adapter<NombresAdapter.NombreViewHolder>() {

    /**
     * En el ViewHolder creamos un método bind que recibe un nombre y lo asigna al TextView del layout.
     * Así facilitamos la actualización de la vista cuando se recicla una celda.
     */
    class NombreViewHolder(val binding: ItemNombreBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(nombre: String){
            binding.tvNombre.text = nombre
            
            binding.root.setOnClickListener {
                Toast.makeText(
                    binding.root.context,
                    "$¡clic sobre ${binding.tvNombre.text}!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NombreViewHolder {
        // Inflamos el layout de la celda
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNombreBinding.inflate(inflater, parent, false)
        return NombreViewHolder(binding)
    }

    override fun getItemCount() = nombres.size

    override fun onBindViewHolder(holder: NombreViewHolder, position: Int) {
        // Obtenemos el nombre de la posición actual
        val nombre = nombres[position]
        // Llamamos al método bind del ViewHolder para asignar el nombre al TextView
        holder.bind(nombre)
    }
}