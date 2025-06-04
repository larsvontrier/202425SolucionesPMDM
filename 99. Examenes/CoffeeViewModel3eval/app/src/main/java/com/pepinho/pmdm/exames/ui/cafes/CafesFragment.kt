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
import androidx.recyclerview.widget.LinearLayoutManager
import com.pepinho.pmdm.exames.CoffeeViewModel
import com.pepinho.pmdm.exames.ui.CoffeeViewModelFactory
import com.pepinho.pmdm.exames.databinding.FragmentCafesBinding
import com.pepinho.pmdm.exames.repository.CafeRepository
import kotlinx.coroutines.launch

class CafesFragment : Fragment() {

    private var _binding: FragmentCafesBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "No puede accedeer al binding porque es nulo. ¿Está visible la vista?"
        }
    

    private val cafesViewModel: CoffeeViewModel by viewModels {
//        val repository = (requireActivity().application as CafeApplication).repository
//        CoffeeViewModelFactory(repository)
        CoffeeViewModelFactory(CafeRepository.get())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCafesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvCafes.layoutManager = LinearLayoutManager(context)

//        // Con RecyclerView.Adapter
//        binding.rvCafes.adapter = CafesAdapter() {
//                idCafe ->
//            Log.d("CafesFragment", "Juego seleccionado: $idCafe")
//            findNavController().navigate(JuegosFragmentDirections.showJuego(idCafe))
//        }

//        // Con ListAdapter
        binding.rvCafes.adapter = CafesListAdapter {
                idCafe ->
            Log.d("CafesFragment", "Juego seleccionado: $idCafe")
            findNavController().navigate(CafesFragmentDirections.showCafe(idCafe))
        }

        // Observar el estado de carga
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                cafesViewModel.loadingState.collect { isLoading ->
                    binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
                }
            }
        }

//        // Observar los datos
        viewLifecycleOwner.lifecycleScope.launch {
            cafesViewModel.getCafes()
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                cafesViewModel.cafes.collect { listaCafes ->
//                    (binding.rvCafes.adapter as CafesAdapter).updateList(listaCafes)
                    // Con ListAdapter
                    (binding.rvCafes.adapter as CafesListAdapter).submitList(listaCafes)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}