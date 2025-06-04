package local.exame.listatareas

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import local.exame.listatareas.model.Tarea

class TareasViewModel : ViewModel() {
    private val _tareas = MutableStateFlow<List<Tarea>>(emptyList())
    val tareas: StateFlow<List<Tarea>> = _tareas.asStateFlow()

    private val _tarea = MutableStateFlow<Tarea?>(null)
    val tarea: StateFlow<Tarea?> = _tarea.asStateFlow()

    private var nextId = 1

    fun addTarea(descripcion: String) {
        if (descripcion.isNotBlank()) {
            val nueva = Tarea(id = nextId++, descripcion = descripcion)
            _tareas.value = _tareas.value + nueva
        }
    }

    fun setCompletada(id: Int, completada: Boolean) {
        _tareas.value = _tareas.value.map {
            if (it.id == id) it.copy(completada = completada) else it
        }
    }

    fun getTareaById(id: Int): Tarea? {
        _tarea.value = _tareas.value.firstOrNull { it.id == id }
        return _tarea.value
    }

    fun deleteTarea(id: Int) {
        // Eliminamos la tarea con el id especificado
//        _tareas.update { current ->
//            current.filter { it.id != id }
//        }
        _tareas.value = _tareas.value.filter { it.id != id }
    }
}