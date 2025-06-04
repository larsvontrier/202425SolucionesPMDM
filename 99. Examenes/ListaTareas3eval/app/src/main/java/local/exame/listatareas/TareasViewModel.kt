package local.exame.listatareas

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import local.exame.listatareas.model.Tarea

class TareasViewModel : ViewModel() {
    private val _tareas = mutableStateListOf<Tarea>() // mutableStateOf<List<Tarea>>()
    val tareas: List<Tarea> = _tareas

    /*
// Con FLow:
private val _tareas = MutableStateFlow<List<Tarea>>(emptyList())

val tareas: StateFlow<List<Tarea>> = _tareas

val tareas by viewModel.tareas.collectAsState()

private val _tareas = MutableStateFlow<List<Tarea>>(emptyList())
val tareas: StateFlow<List<Tarea>> = _tareas

fun agregarTarea(descripcion: String) {
    viewModelScope.launch {
        val nuevaLista = _tareas.value + Tarea(id = ..., descripcion = descripcion)
        _tareas.value = nuevaLista
    }
}
 */

    private var nextId = 1

    fun agregarTarea(descripcion: String) {
        if (descripcion.isNotBlank()) {
            _tareas.add(Tarea(id = nextId++, descripcion = descripcion))
        }
    }

    fun cambiarEstadoTarea(id: Int, completada: Boolean) {
        _tareas.replaceAll { tarea ->
            if (tarea.id == id) tarea.copy(completada = completada) else tarea
        }
    }

    fun eliminarTarea(id: Int) {
        _tareas.removeAll { it.id == id }
    }
}