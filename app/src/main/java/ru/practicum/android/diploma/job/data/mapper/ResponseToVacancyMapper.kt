package ru.practicum.android.diploma.job.data.mapper

import ru.practicum.android.diploma.data.dto.responses.VacancyByIdResponse
import ru.practicum.android.diploma.data.dto.responses.fields.SalaryDTO
import ru.practicum.android.diploma.search.domain.model.Vacancy
import ru.practicum.android.diploma.util.MapperContainer
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

class ResponseToVacancyMapper(
    private val mappers: MapperContainer
) {

    fun map(response: VacancyByIdResponse?): Vacancy {
        return Vacancy(
            address = mappers.addressMapper.map(response?.address),
            alternateUrl = response?.alternateUrl ?: EMPTY_STRING,
            applyAlternateUrl = response?.applyAlternateUrl ?: EMPTY_STRING,
            area = mappers.vacancyAreaMapper.map(response?.area),
            contacts = mappers.contactsMapper.map(response?.contacts),
            description = response?.description ?: EMPTY_STRING,
            employer = mappers.employerMapper.map(response?.employer),
            employment = mappers.employmentMapper.map(response?.employment),
            experience = mappers.experienceMapper.map(response?.experience),
            id = response?.id ?: EMPTY_STRING,
            keySkills = response?.keySkills?.map { dto -> mappers.keySkillMapper.map(dto) } ?: listOf(),
            name = response?.name ?: EMPTY_STRING,
            salary = formatSalary(response?.salary),
            schedule = mappers.scheduleMapper.map(response?.schedule),
        )
    }

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

    companion object {
        private const val EMPTY_STRING = ""
    }
}
