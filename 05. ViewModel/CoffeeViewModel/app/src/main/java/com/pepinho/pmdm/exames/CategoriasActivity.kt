package com.pepinho.pmdm.exames

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.pepinho.pmdm.exames.databinding.ActivityCategoriasBinding
import com.pepinho.pmdm.exames.model.CafeRepository
import com.pepinho.pmdm.exames.model.Categoria
import com.pepinho.pmdm.exames.model.CategoriaCafe
import kotlinx.coroutines.launch

class CategoriasActivity : AppCompatActivity() {
    lateinit var binding: ActivityCategoriasBinding
    lateinit var repository: CafeRepository
    lateinit var categorias: List<Categoria>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCategoriasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        getCategorias()
    }

    private fun getCategorias(){
        lifecycleScope.launch {
            repository = CafeRepository.get()
            categorias = repository.getCategorias()
            updateList(categorias)


        }
    }

    private fun updateList(categorias: List<Categoria>){
        binding.lvCategorias.adapter = CategoriasAdapter(this, R.layout.categoria_item, categorias.toMutableList() )
        Log.d("Categorias: ", "$categorias")
    }

}