package com.pepinho.pmdm.exames

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.pepinho.pmdm.exames.databinding.ActivityCalculatorBinding

class CalorieCalculatorActivity : AppCompatActivity() {
    lateinit var binding: ActivityCalculatorBinding
    var calorias: Int = 0 // Calorias del café

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityCalculatorBinding.inflate(layoutInflater)

        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        calorias = intent.extras?.getInt("calorias") ?: 0

        binding.etTipoCafe.setText("$calorias")

        binding.btCalcular.setOnClickListener {
            calcularCalorias()
        }
    }

    private fun calcularCalorias(){
        binding.etTazas.text.toString().toIntOrNull()?.let {
            val tazas = it
            val total = tazas * calorias
            binding.etCalorias.setText("Calorías: $total Kcal")

            // Porcentaje

            val totalDiario = if(binding.swSexo.isChecked)  1800 else 2200

            val porcentaje = total.toDouble() / totalDiario * 100
            binding.etPorcentaje.setText(String.format("%.2f%% de consumo diario", porcentaje))


        }
    }
}