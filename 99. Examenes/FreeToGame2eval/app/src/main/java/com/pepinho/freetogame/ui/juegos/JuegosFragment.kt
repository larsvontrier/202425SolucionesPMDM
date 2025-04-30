package com.pepinho.freetogame.ui.juegos

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.pepinho.freetogame.JuegoViewModelFactory
import com.pepinho.freetogame.application.GameApplication
import com.pepinho.freetogame.databinding.FragmentJuegosBinding
import kotlinx.coroutines.launch

class JuegosFragment : Fragment() {

    private var _binding: FragmentJuegosBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "No puede accedeer al binding porque es nulo. ¿Está visible la vista?"
        }

    private val args: JuegosFragmentArgs by navArgs()

    private val juegosViewModel: JuegosViewModel by viewModels {
        val repository = (requireActivity().application as GameApplication).respository
        JuegoViewModelFactory(repository, args.idPlataforma)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentJuegosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvJuegos.layoutManager = LinearLayoutManager(context)

//        // Con RecyclerView.Adapter
//        binding.rvJuegos.adapter = JuegosAdapter() {
//                idJuego ->
//            Log.d("JuegosFragment", "Juego seleccionado: $idJuego")
//            findNavController().navigate(JuegosFragmentDirections.showJuego(idJuego))
//        }

//        // Con ListAdapter
        binding.rvJuegos.adapter = JuegosListAdapter {
                idJuego ->
            Log.d("JuegosFragment", "Juego seleccionado: $idJuego")
            findNavController().navigate(JuegosFragmentDirections.showJuego(idJuego))
        }

        // Observar el estado de carga
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                juegosViewModel.loadingState.collect { isLoading ->
                    binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
                }
            }
        }

//        // Observar los datos
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                juegosViewModel.juegos.collect { listaJuegos ->
//                    (binding.rvJuegos.adapter as JuegosAdapter).updateList(listaJuegos)
                    // Con ListAdapter
                    (binding.rvJuegos.adapter as JuegosListAdapter).submitList(listaJuegos)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}