package com.pepinho.pmdm.cuestionarios

import android.os.Bundle
import android.provider.MediaStore.Audio.Radio
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.allViews
import androidx.core.view.get
import androidx.core.view.isVisible
import androidx.transition.Visibility
import com.pepinho.pmdm.cuestionarios.dao.PreguntaDAO
import com.pepinho.pmdm.cuestionarios.model.Cuestionario
import com.pepinho.pmdm.cuestionarios.model.PreguntaTest
import com.pepinho.pmdm.cuestionarios.model.PreguntaVerdaderoFalso

class MainActivity : AppCompatActivity() {

    private lateinit var btLimpar: Button
    private lateinit var btComprobar: Button
    private lateinit var rgOpciones: RadioGroup
    private lateinit var tvPregunta: TextView
    private lateinit var radioButtons: List<RadioButton>
    private lateinit var btNext: Button

    private val cuestionario = Cuestionario(PreguntaDAO().preguntas as MutableList)

    private var iActual: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.principal)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btLimpar = findViewById(R.id.btLimpar)
        btLimpar.setOnClickListener {
            Log.d("MainActivity", "Limpiando selección")
            rgOpciones.clearCheck()
            Toast.makeText(this, "Limpiada selección", Toast.LENGTH_LONG).show()

        }

        btComprobar = findViewById(R.id.btComprobar)
        btComprobar.setOnClickListener {
            checkPregunta()
            Log.d("MainActivity", "Comprobada pregunta")
        }

        rgOpciones = findViewById(R.id.rgOpciones)

        radioButtons = (0 until rgOpciones.childCount)
            .map { rgOpciones.getChildAt(it) }
            .filterIsInstance<RadioButton>()

        rgOpciones.setOnCheckedChangeListener { _, checkedId ->
            // Haz algo en respuesta al cambio de selección aquí
            val radioButton: RadioButton? = findViewById(checkedId)

            // Escribimos en el Logcat el id del botón de opción seleccionado y su texto.
            // Ten en cuenta que cuendo se deselecciona un botón de opción, checkedId es -1 y radioButton es null
            Log.i(
                "Boton con id: $checkedId",
                radioButton?.text.toString()
            )
        }

        tvPregunta = findViewById(R.id.tvPregunta)

        btNext = findViewById<Button?>(R.id.btnNext)
        btNext.setOnClickListener {
            iActual++
            updatePregunta()
        }


        updatePregunta()


    }
    private fun checkPregunta(){
        val pregunta = cuestionario.getPregunta(iActual)
        val seleccionada = rgOpciones.checkedRadioButtonId
        if (seleccionada == -1) {
            Toast.makeText(this, "No has seleccionado ninguna opción", Toast.LENGTH_LONG)
                .show()
        } else {
            val seleccionadaIndex =
                rgOpciones.indexOfChild(findViewById(seleccionada))
            Toast.makeText(
                this,
                if (seleccionadaIndex == (pregunta?.getCorrectIndex() ?: -1)) "¡Correcto!"
                else "¡Incorrecto!", Toast.LENGTH_LONG
            ).show()
        }

    }

    private fun updatePregunta(){
        val pregunta = cuestionario.getPregunta(iActual)
        Log.d("pregunta", pregunta.toString())
        tvPregunta.text = pregunta?.enunciado ?: getString(R.string.noQuestion)

        when(pregunta){
            is PreguntaTest -> {
                radioButtons[0].text = pregunta.opciones[0]?.enunciado
                radioButtons[1].text = pregunta.opciones[1]?.enunciado
                radioButtons[2].text = pregunta.opciones[2]?.enunciado
                radioButtons[3].text = pregunta.opciones[3]?.enunciado
            }
            is PreguntaVerdaderoFalso -> {
                radioButtons[0].text = "Verdadero"
                radioButtons[1].text = "Falso"
                radioButtons[2].isVisible = false
                radioButtons[3].isVisible = false
            }

        }
        if(cuestionario.isLast(iActual)) {
            btNext.visibility = View.GONE
        }
    }

}