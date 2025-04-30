package com.pepinho.nba

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.pepinho.nba.application.NBADataApplication
import com.pepinho.nba.databinding.FragmentEquiposBinding
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator
import kotlinx.coroutines.launch


private const val TAG = "EquiposFragment"

class EquiposFragment : Fragment() {

    private var _binding: FragmentEquiposBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "No puede accedeer al binding porque es nulo. ¿Está visible la vista?"
        }

    private val equiposViewModel: EquiposViewModelStateIn by viewModels {
        val repository = (requireActivity().application as NBADataApplication).respository
        EquipoViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "Total de equipos: ${equiposViewModel.equipos.value.size}")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEquiposBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvEquipos.layoutManager = LinearLayoutManager(context)

        // Animaciones por defecto:
//        binding.rvEquipos.itemAnimator = DefaultItemAnimator() // Animaciones por defecto
        // Animación personalizada:
//        binding.rvEquipos.itemAnimator = SlideInUpAnimator()
        binding.rvEquipos.itemAnimator = SlideInLeftAnimator()

        binding.rvEquipos.adapter = EquiposListAdapter { equipo ->
            Toast.makeText(requireContext(), "${equipo.nombre} ¡clic!", Toast.LENGTH_SHORT).show()
        }

        // Observar el estado de carga
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                equiposViewModel.loadingState.collect { isLoading ->
                    binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
                }
            }
        }

        // Observar los datos
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                equiposViewModel.equipos.collect { listaEquipos ->
                    (binding.rvEquipos.adapter as EquiposListAdapter).submitList(listaEquipos)
                }
            }
        }
    }

//    // Versión con EquiposAdapter que hereda de RecyclerView.Adapter
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        binding.rvEquipos.layoutManager = LinearLayoutManager(context)
//        // Inicialimos la lista vacía, pues lo datos podrían no estar disponibles en el momento de la creación
//        val adapter = EquiposAdapter(emptyList())
//        binding.rvEquipos.adapter = adapter
//
//        viewLifecycleOwner.lifecycleScope.launch {
//            /*
//            Esto funciona, pero no está optimizado para el ciclo de vida del Fragment. Si el Fragment
//             pasa a un estado en el que la vista no está visible (por ejemplo, cuando
//             el usuario navega a otra pantalla), la corrutina seguirá activa y podría desperdiciar recursos.
//             Para optimizar esto, puedes usar viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED).
//             Este método asegura que la corrutina solo se ejecute cuando el Fragment esté en un
//             estado específico del ciclo de vida (en este caso, STARTED o superior).
//             Cuando el Fragment pasa a un estado inferior (como STOPPED), la corrutina
//             se cancela automáticamente.
//             La corrutina solo se ejecutará cuando el Fragment esté en un estado STARTED o superior.
//
//Cuando el Fragment pasa a un estado inferior (como STOPPED), la corrutina se cancela automáticamente, lo que ahorra recursos.
//
//Esto es especialmente útil en casos donde el Fragment no está visible (por ejemplo, cuando el usuario navega a otra pantalla).
//             */
//            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) { // Se ejecuta en estado STARTED o superior. Cuando la corrutina pasa a STOPPED se cancela automáticamente, ahorrando recursos.
//                equiposViewModel.equipos.collect { listaEquipos ->
////                    binding.rvEquipos.adapter = EquiposAdapter(listaEquipos)
//                    adapter.updateList(listaEquipos)
//                    Log.i("Equipos VM", "Recollidos $listaEquipos")
//                }
//            }
//        }
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}