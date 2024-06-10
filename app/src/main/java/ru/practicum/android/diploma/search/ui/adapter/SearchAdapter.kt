package ru.practicum.android.diploma.search.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.data.dto.responses.VacancyByIdResponse

class SearchAdapter(private val searchClickListener: SearchClickListener) : RecyclerView.Adapter<SearchViewHolder>() {

    private var vacancy = ArrayList<VacancyByIdResponse>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_item, parent, false)
        return SearchViewHolder(view)
    }

    override fun getItemCount(): Int = vacancy.size

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(vacancy[position])
        holder.itemView.setOnClickListener { searchClickListener.onVacancyClick(vacancy[position]) }
    }
}
