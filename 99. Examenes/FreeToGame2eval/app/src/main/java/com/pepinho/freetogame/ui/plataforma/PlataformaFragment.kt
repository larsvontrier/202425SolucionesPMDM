package com.pepinho.freetogame.ui.plataforma

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.pepinho.freetogame.databinding.FragmentPlataformaBinding

class PlataformaFragment : Fragment() {

    private var _binding: FragmentPlataformaBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "No puede accedeer al binding porque es nulo. ¿Está visible la vista?"
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlataformaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivBrowser.setOnClickListener {
            findNavController().navigate(PlataformaFragmentDirections.showJuegos(2))
        }

        binding.ivWindows.setOnClickListener {
            findNavController().navigate(PlataformaFragmentDirections.showJuegos(4))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}