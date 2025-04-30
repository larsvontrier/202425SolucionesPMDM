package com.pepinho.pmdm.exames

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.pepinho.pmdm.exames.databinding.CategoriaItemBinding
import com.pepinho.pmdm.exames.model.Categoria

class CategoriasAdapter(context: Context, resource: Int, categorias: MutableList<Categoria>):
    ArrayAdapter<Categoria>(context, resource, categorias) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding: CategoriaItemBinding
        var vistaItem: View
        if (convertView == null) {
            binding = CategoriaItemBinding.inflate(LayoutInflater.from(context))
            vistaItem = binding.root
            vistaItem.tag = binding // Lo uso para guardar el binding en la vista (un cajón para guardar la referencia.
        } else {
            binding = convertView.tag as CategoriaItemBinding // Recuperro el binding
            vistaItem = convertView
        }
        val categoria = getItem(position)

        binding.tvTipo.text = categoria?.nombre ?: "-"

        Log.d("Categoria:", categoria?.nombre ?: "-")

        return vistaItem
    }
}