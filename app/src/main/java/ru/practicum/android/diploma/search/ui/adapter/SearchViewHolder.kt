package ru.practicum.android.diploma.search.ui.adapter

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.data.dto.responses.VacancyByIdResponse
import ru.practicum.android.diploma.databinding.CardItemBinding

class SearchViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    private val binding = CardItemBinding.bind(view)

    @SuppressLint("SetTextI18n")
    fun bind(vacancy: VacancyByIdResponse) = with(binding) {
        Glide.with(itemView)
            .load(vacancy.employer.logoUrls.original)
            .centerCrop()
            .placeholder(R.drawable.ic_placeholder_card_list)
            .into(imgCompany)

        tvVacancy.text = vacancy.name + ", " + vacancy.address.city
        tvCompany.text =
            vacancy.employer.name
        tvSalary.text = vacancy.salary.from.toString() + " " + vacancy.salary.currency

        // пока поставим так, потом при рабочем запросе нужно посмотреть, что покажет и придется поправлять
    }
}
