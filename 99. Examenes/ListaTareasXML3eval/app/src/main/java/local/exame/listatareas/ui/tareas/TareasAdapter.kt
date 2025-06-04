package local.exame.listatareas.ui.tareas

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import local.exame.listatareas.databinding.ItemTareaBinding
import local.exame.listatareas.model.Tarea

class TareasAdapter(
    private var tareas: List<Tarea>,
    private val onCheckedChange: (Int, Boolean) -> Unit,
    private val onDelete: (Int) -> Unit,
    private val onItemClick: (Int) -> Unit
) : RecyclerView.Adapter<TareasAdapter.TareaViewHolder>() {

    fun updateTareas(newTareas: List<Tarea>) {
        tareas = newTareas
        notifyDataSetChanged()
    }

    inner class TareaViewHolder(
        private val binding: ItemTareaBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tarea: Tarea) {
            itemView.apply {
                binding.cbCompletada.apply {
                    isChecked = tarea.completada
                    setOnCheckedChangeListener { _, isChecked ->
                        onCheckedChange(tarea.id, isChecked)
                    }
                }

                binding.tvDescripcion.apply {
                    text = tarea.descripcion
                    paintFlags = if (tarea.completada) {
                        paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                    } else {
                        paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                    }
                }

                binding.btnEliminar.setOnClickListener {
                    onDelete(tarea.id)
                }

                setOnClickListener {
                    onItemClick(tarea.id)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TareaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTareaBinding.inflate(inflater, parent, false)
        return TareaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TareaViewHolder, position: Int) {
        holder.bind(tareas[position])
    }

    override fun getItemCount() = tareas.size
}