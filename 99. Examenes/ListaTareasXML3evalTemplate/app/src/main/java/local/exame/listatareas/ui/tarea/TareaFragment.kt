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
    // TODO: ViewBinding
    // TODO: args
    // TODO: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // TODO: inflate binding. poner como atributo
        val _binding = FragmentTareaBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Obtenemos la tarea del ViewModel

        // Llama solo una vez para actualizar la tarea en el ViewModel.
        // Colectamos el flujo de la tarea en el ViewModel

    }

    override fun onDestroyView() {
        super.onDestroyView()
        // TODO: null binding
    }
}