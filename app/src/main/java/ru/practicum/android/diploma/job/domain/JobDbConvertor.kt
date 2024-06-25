package ru.practicum.android.diploma.job.domain

import ru.practicum.android.diploma.db.entities.VacancyEntity
import ru.practicum.android.diploma.search.domain.model.Vacancy

class JobDbConvertor {
    fun map(vacancy: Vacancy): VacancyEntity {
        return VacancyEntity(
            id = vacancy.id,
            address = vacancy.address,
            alternateUrl = vacancy.alternateUrl,
            applyAlternateUrl = vacancy.applyAlternateUrl,
            area = vacancy.area,
            contacts = vacancy.contacts,
            description = vacancy.description,
            employer = vacancy.employer,
            employment = vacancy.employment,
            experience = vacancy.experience,
            keySkills = vacancy.keySkills,
            name = vacancy.name,
            salary = vacancy.salary,
            schedule = vacancy.schedule,
        )
    }

    fun mapToVacancy(vacancyEntity: VacancyEntity): Vacancy {
        return Vacancy(
            address = vacancyEntity.address,
            alternateUrl = vacancyEntity.alternateUrl,
            applyAlternateUrl = vacancyEntity.applyAlternateUrl,
            area = vacancyEntity.area,
            contacts = vacancyEntity.contacts,
            description = vacancyEntity.description,
            employer = vacancyEntity.employer,
            employment = vacancyEntity.employment,
            experience = vacancyEntity.experience,
            id = vacancyEntity.id,
            keySkills = vacancyEntity.keySkills,
            name = vacancyEntity.name,
            salary = vacancyEntity.salary,
            schedule = vacancyEntity.schedule,
        )
    }

    fun convertToListVacancy(vacancies: List<VacancyEntity>): List<Vacancy> {
        return vacancies.map { vacancy ->
            mapToVacancy(vacancy)
        }
    }
}
