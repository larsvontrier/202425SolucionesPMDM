package local.exame.listatareas.ui.tareas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import local.exame.listatareas.TareasViewModel
import local.exame.listatareas.databinding.FragmentTareasBinding
import kotlin.getValue

class TareasFragment : Fragment() {
    // TODO: ViewBinding

//    TODO: viewModel y adapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // TODO: inflate binding. poner como atributo
        val _binding = FragmentTareasBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // TODO: Cambiar a ViewBinding
    }



    override fun onDestroyView() {
        super.onDestroyView()
        // TODO: null binding
    }
}