package ru.practicum.android.diploma.job.data.mapper

import ru.practicum.android.diploma.data.dto.responses.VacancyByIdResponse
import ru.practicum.android.diploma.data.dto.responses.fields.SalaryDTO
import ru.practicum.android.diploma.search.data.mapper.AddressMapper
import ru.practicum.android.diploma.search.data.mapper.ContactsMapper
import ru.practicum.android.diploma.search.data.mapper.DepartmentMapper
import ru.practicum.android.diploma.search.data.mapper.EmployerMapper
import ru.practicum.android.diploma.search.data.mapper.EmploymentMapper
import ru.practicum.android.diploma.search.data.mapper.ExperienceMapper
import ru.practicum.android.diploma.search.data.mapper.InsiderInterviewMapper
import ru.practicum.android.diploma.search.data.mapper.KeySkillMapper
import ru.practicum.android.diploma.search.data.mapper.ManagerMapper
import ru.practicum.android.diploma.search.data.mapper.ProfessionalRoleMapper
import ru.practicum.android.diploma.search.data.mapper.ScheduleMapper
import ru.practicum.android.diploma.search.data.mapper.SnippetMapper
import ru.practicum.android.diploma.search.data.mapper.TypeMapper
import ru.practicum.android.diploma.search.data.mapper.VacancyAreaMapper
import ru.practicum.android.diploma.search.data.mapper.WorkingDayMapper
import ru.practicum.android.diploma.search.data.mapper.WorkingTimeIntervalMapper
import ru.practicum.android.diploma.search.domain.model.Vacancy
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

class ResponseToVacancyMapper(
    private val addressMapper: AddressMapper,
    private val vacancyAreaMapper: VacancyAreaMapper,
    private val contactsMapper: ContactsMapper,
    private val departmentMapper: DepartmentMapper,
    private val employerMapper: EmployerMapper,
    private val experienceMapper: ExperienceMapper,
    private val insiderInterviewMapper: InsiderInterviewMapper,
    private val keySkillMapper: KeySkillMapper,
    private val managerMapper: ManagerMapper,
    private val professionalRoleMapper: ProfessionalRoleMapper,
    private val scheduleMapper: ScheduleMapper,
    private val typeMapper: TypeMapper,
    private val workingDayMapper: WorkingDayMapper,
    private val workingTimeIntervalMapper: WorkingTimeIntervalMapper,
    private val snippetMapper: SnippetMapper,
    private val employmentMapper: EmploymentMapper,
) {

    fun map(response: VacancyByIdResponse?): Vacancy {
        return Vacancy(
            address = addressMapper.map(response?.address),
            alternateUrl = response?.alternateUrl ?: EMPTY_STRING,
            applyAlternateUrl = response?.applyAlternateUrl ?: EMPTY_STRING,
            approved = response?.approved ?: EMPTY_BOOLEAN,
            archived = response?.archived ?: EMPTY_BOOLEAN,
            area = vacancyAreaMapper.map(response?.area),
            code = response?.code ?: EMPTY_STRING,
            contacts = contactsMapper.map(response?.contacts),
            department = departmentMapper.map(response?.department),
            description = response?.description ?: EMPTY_STRING,
            employer = employerMapper.map(response?.employer),
            employment = employmentMapper.map(response?.employment),
            experience = experienceMapper.map(response?.experience),
            hasTest = response?.hasTest ?: EMPTY_BOOLEAN,
            id = response?.id ?: EMPTY_STRING,
            insiderInterview = insiderInterviewMapper.map(response?.insiderInterview),
            keySkills = response?.keySkills?.map { dto -> keySkillMapper.map(dto) } ?: listOf(),
            manager = managerMapper.map(response?.manager),
            name = response?.name ?: EMPTY_STRING,
            premium = response?.premium ?: EMPTY_BOOLEAN,
            previousId = response?.previousId ?: EMPTY_STRING,
            professionalRoles = response?.professionalRoles?.map { dto -> professionalRoleMapper.map(dto) } ?: listOf(),
            publishedAt = response?.publishedAt ?: EMPTY_STRING,
            responseLetterRequired = response?.responseLetterRequired ?: EMPTY_BOOLEAN,
            responseNotifications = response?.responseNotifications ?: EMPTY_BOOLEAN,
            responseUrl = response?.responseUrl ?: EMPTY_STRING,
            salary = formatSalary(response?.salary),
            schedule = scheduleMapper.map(response?.schedule),
            snippet = snippetMapper.map(response?.snippet),
            type = typeMapper.map(response?.type),
            workingDays = response?.workingDays?.map { dto -> workingDayMapper.map(dto) } ?: listOf(),
            workingTimeIntervals = response?.workingTimeIntervals?.map { response ->
                workingTimeIntervalMapper.map(
                    response
                )
            } ?: listOf()
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
