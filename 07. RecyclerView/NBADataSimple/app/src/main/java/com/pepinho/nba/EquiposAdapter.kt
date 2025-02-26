package com.pepinho.nba

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.pepinho.nba.databinding.ItemEquipoBinding
import com.pepinho.nba.model.Equipo

class EquiposAdapter(
    private var equipos: List<Equipo>
) : RecyclerView.Adapter<EquiposAdapter.EquipoViewHolder>() {

    class EquipoViewHolder(
        private val binding: ItemEquipoBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(equipo: Equipo) {
            equipo?.let {
                binding.tvNombre.text = it.nombre
                binding.tvAbreviatura.text = it.abreviatura

                val resourceId = EquipoIconUtil.getIconResource(it.abreviatura)
                binding.ivEscudo.setImageResource(resourceId)
            }
//            binding.root.setOnClickListener {
//                Toast.makeText(
//                    binding.root.context,
//                    "${equipo.nombre} ¡clic!",
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
        }
    }

    fun updateList(novosEquipos: List<Equipo>){
      equipos = novosEquipos
      notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EquipoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemEquipoBinding.inflate(inflater, parent, false)
        return EquipoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EquipoViewHolder, position: Int) {
        val equipo = equipos[position]
        holder.bind(equipo)
    }

    override fun getItemCount() = equipos.size
}