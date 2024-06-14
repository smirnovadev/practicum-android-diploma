package ru.practicum.android.diploma.filters.ui.industry

import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.databinding.IndustryItemBinding
import ru.practicum.android.diploma.search.domain.model.fields.Industry

class IndustryViewHolder(
    private val binding: IndustryItemBinding,
    val onClickListener: IndustryOnClickListener,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Industry) {
        val context = itemView.context
        with(binding) {
            industryText.text = item.name
        }
        itemView.setOnClickListener { onClickListener.onClick(item) }
    }
}

fun interface IndustryOnClickListener {
    fun onClick(item: Industry)
}
