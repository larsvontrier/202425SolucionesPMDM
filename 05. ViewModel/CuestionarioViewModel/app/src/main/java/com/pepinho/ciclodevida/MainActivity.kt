package com.pepinho.ciclodevida

import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import androidx.core.view.isVisible
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import com.pepinho.ciclodevida.databinding.ActivityMainBinding
import com.pepinho.ciclodevida.repositorio.PreguntaRepository
import com.pepinho.pmdm.cuestionarios.model.Pregunta
import com.pepinho.pmdm.cuestionarios.model.PreguntaTest
import com.pepinho.pmdm.cuestionarios.model.PreguntaVerdaderoFalso

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

//    private val cuestionarioViewModel: CuestionarioViewModel by viewModels()
    private val cuestionarioViewModel: CuestionarioSavedStateViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "llamada a onCreate(Bundle?)")

        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d(TAG, "He creado CuestionarioViewModel: $cuestionarioViewModel")

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btLimpar.setOnClickListener {
            binding.rgOpciones.clearCheck()
            Toast.makeText(this, "Limpiada la selección", Toast.LENGTH_SHORT).show()

        }
        binding.btComprobar.setOnClickListener {
            checkAnswer()
        }

        binding.btnNext.setOnClickListener {
            cuestionarioViewModel.moveNext()
            updatePregunta()
        }

        updatePregunta()
        Log.d("onCreate", "onCreate")

    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "llamada a onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "llamada a onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "llamada a onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "llamada a onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "llamada a onDestroy()")
    }

    /**
     * Actualiza la pregunta actual en la vista
     *
     */
    private fun updatePregunta() {

        binding.tvPregunta.text = String.format(
            getString(R.string.formatoEnunciado), cuestionarioViewModel.numeroPregunta,
            cuestionarioViewModel.enunciado
        )
        updateRadioButtons(cuestionarioViewModel.getOpciones)
        // Es visible si no está en la última pregunta.
        binding.btnNext.isVisible = !cuestionarioViewModel.last
    }

    /* Actualiza las opciones de los botones de radio con los textos proporcionados.
     * Si visible es true, se mostrarán los botones de radio 3 y 4, si no, se ocultarán.
     */
    private fun updateRadioButtons(textos: Array<String>) {
        binding.rgOpciones.clearCheck()
        binding.rbOpcion1.text = textos[0]
        binding.rbOpcion2.text = textos[1]
        val showExtraOptions = textos.size > 2
        binding.rbOpcion3.isVisible = showExtraOptions
        binding.rbOpcion4.isVisible = showExtraOptions
        if (showExtraOptions) {
            binding.rbOpcion3.text = textos[2]
            binding.rbOpcion4.text = textos[3]
        }

    }

    /**
     * Comprueba la respuesta seleccionada por el usuario
     */
    private fun checkAnswer() {
        val seleccionada = binding.rgOpciones.checkedRadioButtonId
        if (seleccionada == -1) {
            Toast.makeText(this, "No has seleccionado ninguna opción", Toast.LENGTH_LONG)
                .show()
        } else {
            val seleccionadaIndex = binding.rgOpciones.indexOfChild(findViewById(seleccionada))
            Toast.makeText(
                this,
                if (seleccionadaIndex == cuestionarioViewModel.respuestaCorrecta) "¡Correcto!"
                else "¡Incorrecto!", Toast.LENGTH_LONG
            ).show()
        }
    }

}