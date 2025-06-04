package local.exame.listatareas.ui.tarea

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import kotlinx.coroutines.launch
import local.exame.listatareas.TareasViewModel
import local.exame.listatareas.databinding.FragmentTareaBinding

class TareaFragment : Fragment() {
    private var _binding: FragmentTareaBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "No puede acceder al binding porque es nulo. ¿Está visible la vista?"
        }
    private val args: TareaFragmentArgs by navArgs()

//    private val viewModel: TareasViewModel by viewModels()
    private val viewModel: TareasViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTareaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("TareaFragment", "idTarea recibido: ${args.tareaId}")

//        val tareaId = arguments?.getInt("tareaId") ?: 0

        // Llama solo una vez para actualizar la tarea en el ViewModel
        val t = viewModel.getTareaById(args.tareaId)

//        Log.d("TareaFragment", "Tarea: $t")


        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.tarea.collect { tarea ->
                    binding.tvCompletada.text = if (tarea?.completada == true) {
                        "Completada"
                    } else {
                        "Pendiente"
                    }
                    binding.tvDescripcion.text = tarea?.descripcion ?: "No hay descripción"
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}