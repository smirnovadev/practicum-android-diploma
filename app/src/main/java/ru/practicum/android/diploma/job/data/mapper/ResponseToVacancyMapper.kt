package ru.practicum.android.diploma.job.data.mapper

import ru.practicum.android.diploma.data.dto.responses.VacancyByIdResponse
import ru.practicum.android.diploma.data.dto.responses.fields.SalaryDTO
import ru.practicum.android.diploma.search.data.mapper.AddressMapper
import ru.practicum.android.diploma.search.data.mapper.ContactsMapper
import ru.practicum.android.diploma.search.data.mapper.EmployerMapper
import ru.practicum.android.diploma.search.data.mapper.EmploymentMapper
import ru.practicum.android.diploma.search.data.mapper.ExperienceMapper
import ru.practicum.android.diploma.search.data.mapper.KeySkillMapper
import ru.practicum.android.diploma.search.data.mapper.ProfessionalRoleMapper
import ru.practicum.android.diploma.search.data.mapper.ScheduleMapper
import ru.practicum.android.diploma.search.data.mapper.VacancyAreaMapper
import ru.practicum.android.diploma.search.domain.model.Vacancy
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

class ResponseToVacancyMapper(
    private val addressMapper: AddressMapper,
    private val vacancyAreaMapper: VacancyAreaMapper,
    private val contactsMapper: ContactsMapper,
    private val employerMapper: EmployerMapper,
    private val experienceMapper: ExperienceMapper,
    private val keySkillMapper: KeySkillMapper,
    private val professionalRoleMapper: ProfessionalRoleMapper,
    private val scheduleMapper: ScheduleMapper,
    private val employmentMapper: EmploymentMapper,
) {

    fun map(response: VacancyByIdResponse?): Vacancy {
        return Vacancy(
            address = addressMapper.map(response?.address),
            alternateUrl = response?.alternateUrl ?: EMPTY_STRING,
            applyAlternateUrl = response?.applyAlternateUrl ?: EMPTY_STRING,
            area = vacancyAreaMapper.map(response?.area),
            code = response?.code ?: EMPTY_STRING,
            contacts = contactsMapper.map(response?.contacts),
            description = response?.description ?: EMPTY_STRING,
            employer = employerMapper.map(response?.employer),
            employment = employmentMapper.map(response?.employment),
            experience = experienceMapper.map(response?.experience),
            id = response?.id ?: EMPTY_STRING,
            keySkills = response?.keySkills?.map { dto -> keySkillMapper.map(dto) } ?: listOf(),
            name = response?.name ?: EMPTY_STRING,
            previousId = response?.previousId ?: EMPTY_STRING,
            professionalRoles = response?.professionalRoles?.map { dto -> professionalRoleMapper.map(dto) } ?: listOf(),
            salary = formatSalary(response?.salary),
            schedule = scheduleMapper.map(response?.schedule),
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
        private const val EMPTY_BOOLEAN = false
    }
}
