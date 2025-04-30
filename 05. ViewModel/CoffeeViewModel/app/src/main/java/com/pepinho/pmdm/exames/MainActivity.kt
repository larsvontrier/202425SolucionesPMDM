package com.pepinho.pmdm.exames

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.pepinho.pmdm.exames.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import com.pepinho.pmdm.exames.model.CafeConCategoria
import com.pepinho.pmdm.exames.model.CafeRepository
import com.pepinho.pmdm.exames.model.setImageFromBytes

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    /**
     * Declaramos el cafeViewModel que se encargará de gestionar la lógica de negocio de la aplicación
     * y de comunicarse con el repositorio.
     * Para ello, utilizamos el método viewModels() que nos proporciona un delegado que nos permite
     * obtener el ViewModel asociado a la actividad.
     * Como recoge un repositorio, necesitamos un ViewModelFactory que se encargue de crear el ViewModel.
     */
    private val cafeViewModel: CoffeeViewModel by viewModels {
        CoffeeViewModelFactory(CafeRepository.get())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btCalcular.setOnClickListener {
            val intent = Intent(this, CalorieCalculatorActivity::class.java)
            intent.putExtra("calorias", cafeViewModel.cafeConCategoria.value.cafe.calorias)
            startActivity(intent)
        }

        binding.btOtro.setOnClickListener {
            cafeViewModel.getCoffeConCategoria()
        }

        binding.btCategorias.setOnClickListener {
            val intent = Intent(this, CategoriasActivity::class.java)
            startActivity(intent)
        }

        /**
         * lifecycleScope es un scope que se encarga de lanzar corutinas que se cancelan cuando la actividad
         * es destruida. En este caso, lanzamos una corutina que se encarga de recoger el flujo de datos
         * que emite el ViewModel y actualiza la interfaz de usuario.
         */
        lifecycleScope.launch {
            cafeViewModel.cafeConCategoria.collect {
                value -> updateUI(value)
            }
        }

    }

    private fun updateUI(value: CafeConCategoria) {
        binding.tvNombre.text = value.cafe.nombre
        binding.imCafe.setImageFromBytes(value.cafe.foto)
        binding.tvTipo.text = value.categoria.nombre
        binding.tvCalorias.text = "${value.cafe.calorias} calorías"
        binding.tvDescripcion.text = value.cafe.descripcion
        binding.btCalcular.isEnabled = true
    }


}




