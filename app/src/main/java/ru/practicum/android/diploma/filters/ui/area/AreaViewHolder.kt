package ru.practicum.android.diploma.filters.ui.area

import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.databinding.AreaItemBinding
import ru.practicum.android.diploma.search.domain.model.fields.Area

class AreaViewHolder(
    private val binding: AreaItemBinding,
    val onClickListener: AreaOnClickListener,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Area) {
        with(binding) {
            areaText.text = item.name
        }
        itemView.setOnClickListener { onClickListener.onClick(item) }
    }
}

fun interface AreaOnClickListener {
    fun onClick(item: Area)
}
