package local.exame.listatareas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import local.exame.listatareas.ui.screens.ListaTareasScreen
import local.exame.listatareas.ui.screens.ListaTareasScreenColumn
import local.exame.listatareas.ui.theme.ListaTareasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ListaTareasTheme {
                    ListaTareasScreen()
            }
        }
    }
}
