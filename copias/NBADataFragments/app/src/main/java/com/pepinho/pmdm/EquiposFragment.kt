package com.pepinho.pmdm

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.pepinho.pmdm.databinding.FragmentEquiposBinding


private const val TAG = "EquiposFragment"

class EquiposFragment : Fragment() {

    private var _binding: FragmentEquiposBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "No puede accedeer al binding porque es nulo. ¿Está visible la vista?"
        }

    private val equiposViewModel: EquiposViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "Total de equipos: ${equiposViewModel.equipos.value?.size}")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEquiposBinding.inflate(inflater, container, false)

        binding.rvEquipos.layoutManager = LinearLayoutManager(context)

        binding.rvEquipos.adapter = EquiposAdapter(emptyList())

        equiposViewModel.equipos.observe(viewLifecycleOwner, {
            equipos ->
            binding.rvEquipos.adapter = EquiposAdapter(equipos)
        })

        equiposViewModel.getEquipost()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}