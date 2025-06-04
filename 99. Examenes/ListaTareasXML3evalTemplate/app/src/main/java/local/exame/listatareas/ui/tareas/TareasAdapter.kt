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
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TareaViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(
        holder: TareaViewHolder,
        position: Int
    ) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }


    inner class TareaViewHolder(
        private val binding: ItemTareaBinding
    ) : RecyclerView.ViewHolder(binding.root) {

    }


}