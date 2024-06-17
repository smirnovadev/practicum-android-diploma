package ru.practicum.android.diploma.search.data.mapper

import ru.practicum.android.diploma.data.dto.responses.fields.SalaryDTO
import ru.practicum.android.diploma.data.dto.responses.vacancies.VacancyDTO
import ru.practicum.android.diploma.search.domain.model.Vacancy
import ru.practicum.android.diploma.util.MapperContainer
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

class VacancyMapper(
    private val mappers: MapperContainer
) {
    private fun formatSalary(dto: SalaryDTO?): String {
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

    fun map(dto: VacancyDTO?): Vacancy {
        return Vacancy(
            address = mappers.addressMapper.map(dto?.address),
            alternateUrl = dto?.alternateUrl ?: EMPTY_STRING,
            applyAlternateUrl = dto?.applyAlternateUrl ?: EMPTY_STRING,
            area = mappers.vacancyAreaMapper.map(dto?.area),
            contacts = mappers.contactsMapper.map(dto?.contacts),
            description = dto?.description ?: EMPTY_STRING,
            employer = mappers.employerMapper.map(dto?.employer),
            employment = mappers.employmentMapper.map(dto?.employment),
            experience = mappers.experienceMapper.map(dto?.experience),
            id = dto?.id ?: EMPTY_STRING,
            keySkills = dto?.keySkills?.map { dto -> mappers.keySkillMapper.map(dto) } ?: listOf(),
            name = dto?.name ?: EMPTY_STRING,
            salary = formatSalary(dto?.salary),
            schedule = mappers.scheduleMapper.map(dto?.schedule),
        )
    }

    companion object {
        private const val EMPTY_STRING = ""
    }
}
