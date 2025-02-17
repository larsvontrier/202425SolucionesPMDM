package com.pepinho.nba

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.pepinho.nba.application.NBADataApplication
import com.pepinho.nba.databinding.FragmentEquipoBinding
import kotlinx.coroutines.launch

private const val TAG = "EquipoFragment"

class EquipoFragment : Fragment(){

    /**
     * Mapa de iconos de los equipos de la NBA.
     * Relaciona la abreviatura de un equipo con el recurso de imagen correspondiente.
     * Es más eficiente que buscar el recurso por nombre cada vez que se actualiza la vista: resources.getIdentifier(...)
     * @see android.content.res.Resources.getIdentifier
     */
    val equipoIconMap = mapOf(
        "atl" to R.drawable.atl,
        "bos" to R.drawable.bos,
        "bkn" to R.drawable.bkn,
        "cha" to R.drawable.cha,
        "chi" to R.drawable.chi,
        "cle" to R.drawable.cle,
        "dal" to R.drawable.dal,
        "den" to R.drawable.den,
        "det" to R.drawable.det,
        "gsw" to R.drawable.gsw,
        "hou" to R.drawable.hou,
        "ind" to R.drawable.ind,
        "lac" to R.drawable.lac,
        "lal" to R.drawable.lal,
        "mem" to R.drawable.mem,
        "mia" to R.drawable.mia,
        "mil" to R.drawable.mil,
        "min" to R.drawable.min,
        "nop" to R.drawable.nop,
        "nyk" to R.drawable.nyk,
        "okc" to R.drawable.okc,
        "orl" to R.drawable.orl,
        "phi" to R.drawable.phi,
        "phx" to R.drawable.phx,
        "por" to R.drawable.por,
        "sac" to R.drawable.sac,
        "sas" to R.drawable.sas,
        "tor" to R.drawable.tor,
        "uta" to R.drawable.uta,
        "was" to R.drawable.was
    )

    private var _binding: FragmentEquipoBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "No puede accedeer al binding porque es nulo. ¿Está visible la vista?"
        }

    private val equipoViewModel: EquipoViewModel by viewModels {
        val repository = (requireActivity().application as NBADataApplication).respository
        EquipoViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "Equipo: ${equipoViewModel.equipo.value}")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEquipoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // La primera vez que se carga la vista se obtiene un equipo aleatorio
        equipoViewModel.getRandomEquipo()

        viewLifecycleOwner.lifecycleScope.launch {
            equipoViewModel.equipo.collect { equipo ->
                equipo?.let {
                    binding.tvAbreviatura.text = it.abreviatura
                    binding.tvNombre.text = it.nombre
                    binding.tvNombreCompleto.text = it.nombreCompleto
                    binding.tvCiudad.text = it.ciudad

                    val resourceId = equipoIconMap[it.abreviatura.lowercase()] ?: R.drawable.nba
                    binding.ivEscudo.setImageResource(resourceId)
//                    val resourceId = resources.getIdentifier(
//                        it.abreviatura.lowercase(),
//                        "drawable",
//                        requireContext().packageName
//                    )
//                    if (resourceId != 0) {
//                        binding.ivEscudo.setImageResource(resourceId)
//                    }
                }
            }
        }

        binding.btResultados.setOnClickListener {
            equipoViewModel.getRandomEquipo()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}