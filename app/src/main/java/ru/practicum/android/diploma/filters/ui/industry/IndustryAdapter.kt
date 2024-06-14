package ru.practicum.android.diploma.filters.ui.industry

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.databinding.IndustryItemBinding
import ru.practicum.android.diploma.search.domain.model.fields.Industry

class IndustryAdapter(
    val list: MutableList<Industry> = mutableListOf(),
    private val onClickListener: IndustryOnClickListener,
) : RecyclerView.Adapter<IndustryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IndustryViewHolder {
        val binding = IndustryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return IndustryViewHolder(binding, onClickListener)
    }

    override fun onBindViewHolder(holder: IndustryViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
        holder.itemView.setOnClickListener { onClickListener.onClick(item) }
    }

    override fun getItemCount() = list.size
}
