package com.pepinho.freetogame.ui.juego

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import coil.load
import com.pepinho.freetogame.JuegoViewModelFactory
import com.pepinho.freetogame.R
import com.pepinho.freetogame.application.GameApplication
import com.pepinho.freetogame.databinding.FragmentJuegoBinding
import kotlinx.coroutines.launch

class JuegoFragment : Fragment() {

    private var _binding: FragmentJuegoBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "No puede acceder al binding porque es nulo. ¿Está visible la vista?"
        }

    private val args: JuegoFragmentArgs by navArgs()

    private val viewModel: JuegoViewModel by viewModels {
        val repository = (requireActivity().application as GameApplication).respository
        JuegoViewModelFactory(repository = repository, idJuego = args.idJuego)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJuegoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("JuegoFragment", "idJuego recibido: ${args.idJuego}")

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.loadingState.collect { loading ->
                if (loading) {
                    binding.progressBar.visibility = View.VISIBLE
                } else {
                    binding.progressBar.visibility = View.GONE
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.juego.collect { juego ->
                juego?.let {
                    // Cargar la imagen desde la URL
                    binding.miniaturaImageView.load(it.juego.miniatura) {
                        placeholder(R.drawable.ic_launcher_foreground) // Imagen de placeholder
                        error(R.drawable.game) // Imagen en caso de error
                    }
                    // Actualizar otros campos
                    binding.tituloTextView.text = it.juego.titulo
                    binding.generoTextView.text = "Género: ${it.genero.nombre}"
                    binding.fechaTextView.text = it.juego.fecha.toString()
                    binding.plataformaTextView.text = it.plataforma.nombre
                    binding.desarrolladorTextView.text = it.juego.desarrollador
                    binding.urlTextView.text = it.juego.url
                    binding.descripcionTextView.text = it.juego.descripcion

                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}