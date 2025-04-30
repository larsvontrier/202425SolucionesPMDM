package com.pepinho.nba.ui.equipos

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
//import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.pepinho.nba.MainActivity
import com.pepinho.nba.R
import com.pepinho.nba.ui.EquipoViewModelFactory
import com.pepinho.nba.application.NBADataApplication
import com.pepinho.nba.databinding.FragmentEquiposBinding
import com.pepinho.nba.model.Equipo
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
        // Modo clásico:
//        setHasOptionsMenu(true) // Menu Provider es la alternativa.
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

        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Añade los elementos de menú aquí
                menuInflater.inflate(R.menu.fragment_equipos, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                // Gestiona la selección del menú
                return when (menuItem.itemId) {
                    R.id.novo_equipo -> {
                        Log.d("Novo equipo", "Navego al nuevo fragmento")
//                showNovoEquipo() // por implementar
                        true
                    }

                    R.id.configuracion -> {
                        Log.d("Configuración", "Configuración de la App")
                        true
                    }

                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        (activity as MainActivity).supportActionBar?.title = "Lista NBA"

        binding.rvEquipos.layoutManager = LinearLayoutManager(context)

        // Animaciones por defecto:
//        binding.rvEquipos.itemAnimator = DefaultItemAnimator() // Animaciones por defecto
        // Animación personalizada:
//        binding.rvEquipos.itemAnimator = SlideInUpAnimator()

//        binding.rvEquipos.adapter = EquiposListAdapter { equipo ->
//            Toast.makeText(requireContext(), "${equipo.nombre} ¡clic!", Toast.LENGTH_SHORT).show()
//        }

        binding.rvEquipos.adapter = EquiposAdapter(emptyList()) {
//            findNavController().navigate(R.id.show_detalle_equipo)
                idEquipo ->
            findNavController().navigate(EquiposFragmentDirections.showDetalleEquipo(idEquipo))

        }

        // Observar el estado de carga
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                equiposViewModel.loadingState.collect { isLoading ->
                    binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
                }
            }
        }

//        // Observar los datos
//        viewLifecycleOwner.lifecycleScope.launch {
//            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
//                equiposViewModel.equipos.collect { listaEquipos ->
//                    (binding.rvEquipos.adapter as EquiposListAdapter).submitList(listaEquipos)
//                }
//            }
//        }

        // Observar los datos
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                equiposViewModel.equipos.collect { listaEquipos ->
                    (binding.rvEquipos.adapter as EquiposAdapter).updateList(listaEquipos)
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
// Modo clásico:
//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        super.onCreateOptionsMenu(menu, inflater)
//        inflater.inflate(R.menu.fragment_equipos, menu)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return when (item.itemId) {
//            R.id.novo_equipo -> {
//                Log.d("Novo equipo", "Navego al nuevo fragmento")
////                showNovoEquipo() // por implementar
//                true
//            }
//            R.id.configuracion -> {
//                Log.d("Configuración", "Configuración de la App")
//                true
//            }
//            else -> super.onOptionsItemSelected(item)
//        }
//    }

    private fun showNovoEquipo() {
        viewLifecycleOwner.lifecycleScope.launch {
            // Podríamos añadir un nuevo equipo aquí o añadirlo en el ViewModel (mejor)
            val equipo = Equipo(
                // ...
            )
//            equiposViewModel.addEquipo(equipo)
            findNavController().navigate(
                EquiposFragmentDirections.showDetalleEquipo(equipo.idEquipo)
            )
        }
    }

}