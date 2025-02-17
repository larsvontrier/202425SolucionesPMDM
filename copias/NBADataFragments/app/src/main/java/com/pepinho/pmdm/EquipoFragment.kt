package com.pepinho.pmdm

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.pepinho.pmdm.databinding.FragmentEquipoBinding
import kotlinx.coroutines.launch

private const val TAG = "EquipoFragment"

class EquipoFragment : Fragment(){

    private var _binding: FragmentEquipoBinding? = null

    private val equipoViewModel: EquipoViewModel by viewModels()

    private val binding
        get() = checkNotNull(_binding) {
            "No puede accedeer al binding porque es nulo. ¿Está visible la vista?"
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "Equipo: ${equipoViewModel.equipo.value}")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEquipoBinding.inflate(inflater, container,false)

        viewLifecycleOwner.lifecycleScope.launch {
            equipoViewModel.equipo.collect {
                    equipo ->
                binding.etAbreviatura.setText(equipo.abreviatura)
                binding.etNombre.setText(equipo.nombre)
                binding.etNombreCompleto.setText(equipo.nombreCompleto)
            }
        }

        return super.onCreateView(inflater, container, savedInstanceState)
    }
}