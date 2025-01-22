package com.pepinho.pmdm.exames

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
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
    lateinit var cafeConCategoria: CafeConCategoria
    lateinit var repository: CafeRepository

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

        repository = CafeRepository.get()
        getCoffee()

        binding.btCalcular.setOnClickListener {
            val intent = Intent(this, CalorieCalculatorActivity::class.java)
            intent.putExtra("calorias", cafeConCategoria.cafe.calorias)
            startActivity(intent)
        }

        binding.btOtro.setOnClickListener {
            getCoffee()
        }

        binding.btCategorias.setOnClickListener {
            val intent = Intent(this, CategoriasActivity::class.java)
            startActivity(intent)
        }

    }

    private fun getCoffee(){
        lifecycleScope.launch {
//            repository = CafeRepository.get()
            cafeConCategoria = repository.getCafeConCategoriaRandom()
//            cafe = repo.getCafes().random()
//            cafe = repo.getCafeById(6)
            updateUI()
        }
    }

    private fun updateUI(){
        binding.tvNombre.text = cafeConCategoria.cafe.nombre
        binding.imCafe.setImageFromBytes(cafeConCategoria.cafe.foto)
        binding.tvTipo.text = cafeConCategoria.categoria.nombre
        binding.tvCalorias.text = "${cafeConCategoria.cafe.calorias} calorías"
        binding.tvDescripcion.text = cafeConCategoria.cafe.descripcion
        binding.btCalcular.isEnabled = true
    }


}




