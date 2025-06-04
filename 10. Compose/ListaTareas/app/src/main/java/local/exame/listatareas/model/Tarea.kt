package local.exame.listatareas.model

data class Tarea(
    val id: Int,
    val descripcion: String,
    val completada: Boolean = false
)