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

class TareasFragment : Fragment() {
    private var _binding: FragmentTareasBinding? = null
    private val binding get() = _binding!!

//    private val viewModel: TareasViewModel by viewModels()
    private val viewModel: TareasViewModel by activityViewModels()
    private lateinit var adapter: TareasAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTareasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupListeners()
        collectFlows()
    }

    private fun setupRecyclerView() {
        adapter = TareasAdapter(
            emptyList(),
            onCheckedChange = { id, completada ->
                viewModel.setCompletada(id, completada)
            },
            onDelete = { id ->
                viewModel.deleteTarea(id)
            },
            onItemClick = { id ->
                findNavController().navigate(
                    TareasFragmentDirections.showTarea(id)
                )
            }
        )

        binding.rvTareas.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@TareasFragment.adapter
        }
    }

    private fun setupListeners() {
        binding.btnAgregar.setOnClickListener {
            addTask()
        }

        binding.etNuevaTarea.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                addTask()
                true
            } else {
                false
            }
        }
    }

    private fun addTask() {
        val descripcion = binding.etNuevaTarea.text.toString()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                if (descripcion.isNotBlank()) {
                    viewModel.addTarea(descripcion)
                }
            }
            binding.etNuevaTarea.text?.clear()
        }

    }

    private fun collectFlows() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.tareas.collect { tareas ->
                    adapter.updateTareas(tareas)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}