package ru.practicum.android.diploma.search.data.mapper

import ru.practicum.android.diploma.data.dto.responses.fields.Salary
import ru.practicum.android.diploma.data.dto.responses.fields.SearchVacanciesUnit
import ru.practicum.android.diploma.search.domain.model.VacancySnippet
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

class VacancyMapper {
    fun map(dto: SearchVacanciesUnit): VacancySnippet {
        return VacancySnippet(
            name = dto.name ?: "",
            id = dto.id,
            salary = formatSalary(dto.salary),
            companyName = dto.employer.name ?: "",
            logoUrl = dto.employer.url ?: ""
        )
    }

    private fun formatSalary(dto: Salary?): String {
        if (dto == null) {
            return "Зарплата не указана"
        }

        val from = dto.from
        val to = dto.to
        val currency = dto.currency ?: ""

        val currencyMap = mapOf(
            "RUR" to "₽",
            "RUB" to "₽",
            "BYR" to "Br",
            "USD" to "$",
            "EUR" to "€",
            "KZT" to "₸",
            "UAH" to "₴",
            "AZN" to "₼",
            "UZS" to "лв",
            "GEL" to "ლ",
            "KGT" to "с"
        )

        val currencySymbol = currencyMap[currency] ?: currency

        val symbols = DecimalFormatSymbols(Locale.getDefault()).apply {
            groupingSeparator = ' '
        }

        val formatter = DecimalFormat("#,###", symbols)

        return when {
            from != null && to != null -> "От ${formatter.format(from)} до ${formatter.format(to)} $currencySymbol"
            from != null -> "От ${formatter.format(from)} $currencySymbol"
            to != null -> "До ${formatter.format(to)} $currencySymbol"
            else -> "Зарплата не указана"
        }
    }

}
