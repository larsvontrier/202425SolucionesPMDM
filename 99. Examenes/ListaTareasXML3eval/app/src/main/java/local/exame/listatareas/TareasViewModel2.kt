package local.exame.listatareas

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import local.exame.listatareas.model.Tarea

class TareasViewModel2 : ViewModel() {
    private val _tareas = MutableStateFlow<List<Tarea>>(emptyList())
    val tareas: StateFlow<List<Tarea>> = _tareas.asStateFlow()

    private val _tarea = MutableStateFlow<Tarea?>(null)
    val tarea: StateFlow<Tarea?> = _tarea.asStateFlow()

    private var nextId = 1

    fun addTarea(descripcion: String) {
        if (descripcion.isNotBlank()) {
            _tareas.update { current ->
                current + Tarea(id = nextId++, descripcion = descripcion)
            }
        }
    }

    fun setCompletada(id: Int, completada: Boolean) {
        _tareas.update { current ->
            current.map {
                if (it.id == id) it.copy(completada = completada) else it
            }
        }
    }

    fun getTareaById(id: Int): Tarea? {
        val t = _tareas.value.firstOrNull { it.id == id }
        _tarea.value = t?.copy()
        return t
    }

    fun eliminarTarea(id: Int) {
        // Eliminamos la tarea con el id especificado
        _tareas.update { current ->
            current.filter { it.id != id }
        }
    }
}