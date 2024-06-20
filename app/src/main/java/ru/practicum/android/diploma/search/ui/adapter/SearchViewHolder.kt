package ru.practicum.android.diploma.search.ui.adapter

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.CardItemBinding
import ru.practicum.android.diploma.search.domain.model.Vacancy

class SearchViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    private val binding = CardItemBinding.bind(view)

    @SuppressLint("SetTextI18n")
    fun bind(vacancy: Vacancy) = with(binding) {
        val cornerSize = itemView.resources.getDimensionPixelSize(R.dimen._12dp)
        Glide.with(itemView)
            .load(vacancy.employer.logoUrls?.original)
            .centerCrop()
            .placeholder(R.drawable.ic_placeholder_card_list)
            .transform(RoundedCorners(cornerSize))
            .into(imgCompany)

        if (vacancy.address.city.isEmpty()) {
            tvVacancy.text = vacancy.name + ", " + vacancy.area.name
        } else {
            tvVacancy.text = vacancy.name + ", " + vacancy.address.city
        }
        tvCompany.text = vacancy.employer.name
        tvSalary.text = vacancy.salary

    }
}
