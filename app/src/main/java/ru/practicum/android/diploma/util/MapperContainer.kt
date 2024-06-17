package ru.practicum.android.diploma.util

import ru.practicum.android.diploma.search.data.mapper.AddressMapper
import ru.practicum.android.diploma.search.data.mapper.ContactsMapper
import ru.practicum.android.diploma.search.data.mapper.EmployerMapper
import ru.practicum.android.diploma.search.data.mapper.EmploymentMapper
import ru.practicum.android.diploma.search.data.mapper.ExperienceMapper
import ru.practicum.android.diploma.search.data.mapper.KeySkillMapper
import ru.practicum.android.diploma.search.data.mapper.ScheduleMapper
import ru.practicum.android.diploma.search.data.mapper.VacancyAreaMapper

data class MapperContainer(
    val addressMapper: AddressMapper,
    val vacancyAreaMapper: VacancyAreaMapper,
    val contactsMapper: ContactsMapper,
    val employerMapper: EmployerMapper,
    val experienceMapper: ExperienceMapper,
    val keySkillMapper: KeySkillMapper,
    val scheduleMapper: ScheduleMapper,
    val employmentMapper: EmploymentMapper
)

