package com.pepinho.questiontest

import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    companion object {
        const val CORRECT_INDEX = 2
    }

    private lateinit var btLimpar: Button
    private lateinit var btComprobar: Button
    private lateinit var rgOpciones: RadioGroup

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
        btComprobar = findViewById(R.id.btComprobar)
        rgOpciones = findViewById(R.id.rgOpciones)

        /**
         * 1. Muestra un mensaje en el Logcat. Existen varios métodos estáticos en la clase Log
         *             que permiten mostrar mensajes de depuración en el Logcat: Log.d(), Log.e(), Log.i(), Log.v(), Log.w(), Log.wtf()
         *             d -> debug
         *             e -> error
         *             i -> información
         *             v -> verbose
         *             w -> advertencia
         *             wtf -> what a terrible failure
         * 2. Deseleccionar todos los botones de opción en el grupo con el método clearCheck()
         * 3. Muestra un mensaje Toast con la cadena "Limpiada selección" y duración Toast.LENGTH_LONG
         * La duranción puede ser Toast.LENGTH_SHORT (1 o 2 segundos) o Toast.LENGTH_LONG (2 o 3 segundos)
         */
        btLimpar.setOnClickListener {
            Log.d("MainActivity", "Limpiando selección")
            rgOpciones.clearCheck()
            // Es necesario llamar a show() para mostrar el Toast
            Toast.makeText(this, "Limpiada selección", Toast.LENGTH_LONG).show()

        }
        btComprobar.setOnClickListener {
            // Invoco a la función getCheckedIndex pasando el id del botón de opción seleccionado
            // Dicha función la he creado para que sea más fácil de leer y entender el código.
            getCheckedIndex(rgOpciones.checkedRadioButtonId)
            Log.d(null, "clic")
        }
        /**
         * 1. Establece un listener para el grupo de botones de opción
         * 2. OnCheckedChangeListener es una interfaz de un único método *onCheckedChanged()
         * que se utiliza para recibir notificaciones cuando el estado de un botón de opción cambia
         * 3. El método onCheckedChanged() se llama cuando el estado de un botón de opción cambia y recoge dos parámetros:
         *     - group: el grupo de botones de opción
         *     - checkedId: el identificador del botón de opción seleccionado
         */
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


    }

    /**
     * 1. Recoge el índice del botón de opción seleccionado en el grupo de botones de opción
     * 2. Muestra un mensaje Toast con la cadena "Correcto" si el índice es igual a CORRECT_INDEX
     *   o "Incorrecto" si el índice es diferente a CORRECT_INDEX
     *   y duración Toast.LENGTH_LONG
     */
    private fun getCheckedIndex(selectedId: Int) {
        if (selectedId != -1) {
            val radioButton = findViewById<RadioButton>(selectedId)
            // Recoge el índice del botón de opción seleccionado en el grupo de botones de opción
            val index = rgOpciones.indexOfChild(radioButton)
            // Ahora puedes usar el índice
//            Toast.makeText(this,
//                if(index==CORRECT_INDEX) R.string.okMensaje else R.string.errorMensaje,
//                Toast.LENGTH_SHORT).show()

            val sb = Snackbar.make(
                btComprobar,
                if (index == CORRECT_INDEX) R.string.okMensaje else R.string.errorMensaje,
                Snackbar.LENGTH_INDEFINITE
            )

            // Situo la vita sobre el botón de comprobar
            sb.anchorView = btComprobar
            sb.setAction("Cerrar") {
                // cierra el snackbar:
                sb.dismiss()
            }
                .setBackgroundTintMode(PorterDuff.Mode.MULTIPLY)
                .show()

        } else {
            Toast.makeText(this, "No hay selección", Toast.LENGTH_LONG).show()
        }

    }
}