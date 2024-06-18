package ru.practicum.android.diploma.filters.ui.industry

import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.databinding.IndustryItemBinding
import ru.practicum.android.diploma.search.domain.model.fields.Industry

class IndustryViewHolder(
    private val binding: IndustryItemBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Industry) {
        with(binding) {
            industryText.text = item.name
        }
    }

    fun check(status: Boolean = true) {
        binding.industryButton.isChecked = status
    }
}

fun interface IndustryOnClickListener {
    fun onClick(item: Industry)
}
