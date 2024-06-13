package ru.practicum.android.diploma.search.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.search.domain.model.Vacancy

class SearchAdapter(private val searchClickListener: SearchClickListener) : RecyclerView.Adapter<SearchViewHolder>() {

    var vacanciesList = ArrayList<Vacancy>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_item, parent, false)
        return SearchViewHolder(view)
    }
    override fun getItemCount(): Int = vacanciesList.size

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(vacanciesList[position])
        holder.itemView.setOnClickListener { searchClickListener.onVacancyClick(vacanciesList[position]) }
    }
}
