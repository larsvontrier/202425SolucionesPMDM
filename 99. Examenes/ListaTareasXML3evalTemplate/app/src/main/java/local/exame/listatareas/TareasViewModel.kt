package local.exame.listatareas

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import local.exame.listatareas.model.Tarea

class TareasViewModel : ViewModel() {

    private var nextId = 1

    fun addTarea(descripcion: String) {
    }

    fun setCompletada(id: Int, completada: Boolean) {
    }

    fun getTareaById(id: Int): Tarea? {
        return null
    }

    fun deleteTarea(id: Int) {
    }
}