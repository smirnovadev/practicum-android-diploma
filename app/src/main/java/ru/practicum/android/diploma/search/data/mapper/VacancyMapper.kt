package ru.practicum.android.diploma.search.data.mapper

import ru.practicum.android.diploma.data.dto.responses.fields.SalaryDTO
import ru.practicum.android.diploma.data.dto.responses.vacancies.VacancyDTO
import ru.practicum.android.diploma.search.domain.model.Vacancy
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

class VacancyMapper(
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
    private val workingTimeIntervalMapper: WorkingTimeIntervalMapper
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
            address = addressMapper.map(dto?.address),
            alternateUrl = dto?.alternateUrl ?: EMPTY_STRING,
            applyAlternateUrl = dto?.applyAlternateUrl ?: EMPTY_STRING,
            approved = dto?.approved ?: EMPTY_BOOLEAN,
            archived = dto?.archived ?: EMPTY_BOOLEAN,
            area = vacancyAreaMapper.map(dto?.area),
            code = dto?.code ?: EMPTY_STRING,
            contacts = contactsMapper.map(dto?.contacts),
            department = departmentMapper.map(dto?.department),
            description = dto?.description ?: EMPTY_STRING,
            employer = employerMapper.map(dto?.employer),
            experience = experienceMapper.map(dto?.experience),
            hasTest = dto?.hasTest ?: EMPTY_BOOLEAN,
            id = dto?.id ?: EMPTY_STRING,
            insiderInterview = insiderInterviewMapper.map(dto?.insiderInterview),
            keySkills = dto?.keySkills?.map { dto -> keySkillMapper.map(dto) } ?: listOf(),
            manager = managerMapper.map(dto?.manager),
            name = dto?.name ?: EMPTY_STRING,
            premium = dto?.premium ?: EMPTY_BOOLEAN,
            previousId = dto?.previousId ?: EMPTY_STRING,
            professionalRoles = dto?.professionalRoles?.map { dto -> professionalRoleMapper.map(dto) } ?: listOf(),
            publishedAt = dto?.publishedAt ?: EMPTY_STRING,
            responseLetterRequired = dto?.responseLetterRequired ?: EMPTY_BOOLEAN,
            responseNotifications = dto?.responseNotifications ?: EMPTY_BOOLEAN,
            responseUrl = dto?.responseUrl ?: EMPTY_STRING,
            salary = formatSalary(dto?.salary),
            type = typeMapper.map(dto?.type),
            schedule = scheduleMapper.map(dto?.schedule),
            workingDays = dto?.workingDays?.map { dto -> workingDayMapper.map(dto) } ?: listOf(),
            workingTimeIntervals = dto?.workingTimeIntervals?.map { dto -> workingTimeIntervalMapper.map(dto) }
                ?: listOf()
        )
    }

    companion object {
        private const val EMPTY_STRING = ""
        private const val EMPTY_BOOLEAN = false
    }
}
