package com.pepinho.pmdm.recyclerviewsimple

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.pepinho.pmdm.recyclerviewsimple.databinding.ActivityMainBinding

// Lista de nombres de filósofos del siglo XX
private val nombres = listOf("Karl Marx", "Bertrand Russell", "Ludwig Wittgenstein",
    "Jean-Paul Sartre", "Simone de Beauvoir", "Albert Camus", "Martin Heidegger", "Hannah Arendt",
    "Theodor Adorno", "Walter Benjamin", "Gilles Deleuze", "Michel Foucault", "Jacques Derrida",
    "Jean Baudrillard", "Slavoj Žižek", "Engels", "Friedrich Nietzsche", "Sigmund Freud",
    "Judith Butler", "Martha Nussbaum", "Richard Rorty", "John Rawls", "Robert Nozick")

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        binding.rvNombres.layoutManager = LinearLayoutManager(this)
//        binding.rvNombres.layoutManager = GridLayoutManager(this, 2)
//        binding.rvNombres.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL)
//        binding.rvNombres.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvNombres.layoutManager = LinearLayoutManager(this)
        binding.rvNombres.adapter = NombresAdapter(nombres)
        binding.rvNombres.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    }
}