package ru.practicum.android.diploma.filters.ui.industry

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.databinding.IndustryItemBinding
import ru.practicum.android.diploma.search.domain.model.fields.Industry

class IndustryAdapter(
    val list: MutableList<Industry> = mutableListOf(),
    var selectedId: Int? = null,
    private val onClickListener: IndustryOnClickListener,
) : RecyclerView.Adapter<IndustryViewHolder>() {
    private var lastChecked: IndustryViewHolder? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IndustryViewHolder {
        val binding = IndustryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return IndustryViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: IndustryViewHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {
        val item = list[position]
        holder.bind(item)

        val listener: (v: View) -> Unit = {
            lastChecked?.check(false)
            lastChecked = holder
            selectedId = position
            holder.check(true)
            onClickListener.onClick(item)
        }
        holder.itemView.setOnClickListener(listener)
        holder.check(position == selectedId)
        if (position == selectedId)
            lastChecked = holder
    }

    override fun getItemCount() = list.size
}
