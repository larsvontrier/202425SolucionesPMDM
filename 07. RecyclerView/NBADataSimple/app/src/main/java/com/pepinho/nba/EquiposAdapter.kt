package com.pepinho.nba

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.pepinho.nba.databinding.ItemEquipoBinding
import com.pepinho.nba.model.Equipo

class EquipoHolder(
    private val binding: ItemEquipoBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(equipo: Equipo) {
        binding.tvNombre.text = equipo.nombre
        binding.tvAbreviatura.text = equipo.abreviatura

        binding.ivEscudo.setImageResource(binding.root.resources.getIdentifier(equipo.abreviatura.lowercase(), "drawable", binding.root.context.packageName))

        binding.root.setOnClickListener {
            Toast.makeText(
                binding.root.context,
                "${equipo.nombre} ¡clic!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}

class EquiposAdapter(
    private val equipos: List<Equipo>
) : RecyclerView.Adapter<EquipoHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EquipoHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemEquipoBinding.inflate(inflater, parent, false)
        return EquipoHolder(binding)
    }

    override fun onBindViewHolder(holder: EquipoHolder, position: Int) {
        val equipo = equipos[position]
        holder.bind(equipo)
    }

    override fun getItemCount() = equipos.size
}