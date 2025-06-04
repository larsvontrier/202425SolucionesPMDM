package com.pepinho.pmdm.exames.ui.cafes


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pepinho.pmdm.exames.databinding.CafeItemBinding
import com.pepinho.pmdm.exames.model.Cafe
import com.pepinho.pmdm.exames.model.setImageFromBytes


class CafesAdapter(
    private val onItemClickListener: (Int) -> Unit
) : RecyclerView.Adapter<CafesAdapter.CafesViewHolder>() {
    private var cafes: List<Cafe> = emptyList()

    class CafesViewHolder(
        private val binding: CafeItemBinding,
        private val onItemClickListener: (Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(cafe: Cafe) {
            cafe?.let {
                binding.tvNombreCafe.text = it.nombre
                binding.tvTipoCafe.text = it.descripcion?:"_"
                binding.cafeImageView.setImageFromBytes(it.foto)
                binding.root.setOnClickListener {
                    onItemClickListener(cafe.idCafe)
                }
            }
        }
    }

    fun updateList(novosJuegos: List<Cafe>){
        cafes = novosJuegos
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CafesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CafeItemBinding.inflate(inflater, parent, false)
        return CafesViewHolder(binding, onItemClickListener)
    }

    override fun onBindViewHolder(holder: CafesViewHolder, position: Int) {
        val cafe = cafes[position]
        holder.bind(cafe)
    }

    override fun getItemCount() = cafes.size
}