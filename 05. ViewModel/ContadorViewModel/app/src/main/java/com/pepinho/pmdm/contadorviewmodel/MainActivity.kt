package com.pepinho.pmdm.contadorviewmodel

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.pepinho.pmdm.contadorviewmodel.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: ContadorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        enableEdgeToEdge()

        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        binding.btIncrementar.setOnClickListener {
            viewModel.incremantar()
        }

        binding.btDecrementar.setOnClickListener {
            viewModel.decrementar()
        }

        /**
         * Observamos el valor del contador y lo mostramos en el TextView
         * collect es un método de la librería kotlinx.coroutines.flow que
         * nos permite observar un flujo de datos. Recoge el valor del flujo
         * y lo muestra en el TextView
         */
        lifecycleScope.launch {
            viewModel.valor.collect { numero ->
                binding.tvNumero.text = numero.toString()

            }
        }




    }
}