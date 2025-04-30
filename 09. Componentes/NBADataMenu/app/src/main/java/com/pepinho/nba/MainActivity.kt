package com.pepinho.nba

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.pepinho.nba.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()

        // Configurar la Toolbar
        setSupportActionBar(binding.toolbar)

        // Obtener NavController CORRECTAMENTE
//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.contendor_fragmento) as NavHostFragment
//        val navController = navHostFragment.navController

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.contendor_fragmento) as NavHostFragment
        val navController = navHostFragment.navController

        // Configurar la Toolbar con el NavController
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        // Usando la versión con AppBarConfiguration
        binding.toolbar.setupWithNavController(
            navController = navController,
            configuration = appBarConfiguration // Asegúrate de crear esta variable
        )

        // Window insets
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }


}