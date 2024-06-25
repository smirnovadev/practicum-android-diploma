package ru.practicum.android.diploma.filters.ui.area

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.databinding.AreaItemBinding
import ru.practicum.android.diploma.search.domain.model.fields.Area

class AreaAdapter(
    val list: MutableList<Area> = mutableListOf(),
    private val onClickListener: AreaOnClickListener,
) : RecyclerView.Adapter<AreaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AreaViewHolder {
        val binding = AreaItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AreaViewHolder(binding, onClickListener)
    }

    override fun onBindViewHolder(holder: AreaViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
        holder.itemView.setOnClickListener { onClickListener.onClick(item) }
    }

    override fun getItemCount() = list.size
}
