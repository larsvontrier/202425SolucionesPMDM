package com.pepinho.nba.ui.equipos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pepinho.nba.ui.util.EquipoIconUtil
import com.pepinho.nba.databinding.ItemEquipoBinding
import com.pepinho.nba.model.Equipo

class EquiposAdapter(
    private var equipos: List<Equipo>,
    private val onEquipoClicked: (Int) -> Unit
) : RecyclerView.Adapter<EquiposAdapter.EquipoViewHolder>() {

    class EquipoViewHolder(
        private val binding: ItemEquipoBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(equipo: Equipo, onEquipoClicked: (Int) -> Unit) {
            equipo?.let {
                binding.tvNombre.text = it.nombre
                binding.tvAbreviatura.text = it.abreviatura

                val resourceId = EquipoIconUtil.getIconResource(it.abreviatura)
                binding.ivEscudo.setImageResource(resourceId)
            }
            binding.root.setOnClickListener {
//                Toast.makeText(
//                    binding.root.context,
//                    "${equipo.nombre} ¡clic!",
//                    Toast.LENGTH_SHORT
//                ).show()
                onEquipoClicked(equipo.idEquipo)
            }

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
        holder.bind(equipo, onEquipoClicked)
    }

    override fun getItemCount() = equipos.size
}