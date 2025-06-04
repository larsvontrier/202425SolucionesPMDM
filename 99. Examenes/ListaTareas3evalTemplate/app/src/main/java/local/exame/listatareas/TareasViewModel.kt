package local.exame.listatareas

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import local.exame.listatareas.model.Tarea

class TareasViewModel : ViewModel() {
    // Estado de la lista de tareas (mutableStateListOf permite la reactividad)


    private var nextId = 1

    fun agregarTarea(descripcion: String) {
    }

    fun cambiarEstadoTarea(id: Int, completada: Boolean) {
    }

    fun eliminarTarea(id: Int) {
    }
}