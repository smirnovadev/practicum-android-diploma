package ru.practicum.android.diploma.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vacancy_table")
data class VacancyEntity(

    @PrimaryKey
    val id: Int,

    // Название вакансии
    val name: String,

    // Адресс
    val address: String,

    // Зарплата
    val salary: String,

    // Поля работодателя в вакансии
    val employer: String,

    // Департамент
    val department: String,

    //Опыт работы
    val experience: String,

    // Дни работы
    val workingDays: String,

    // График работы
    val schedule: String,

    // Список профессиональных ролей
    val professionalRoles: String,

    // Тип вакансии
    val type: String,

    // Регион
    val area: String,

    // Описание
    val description: String,

    // Ключевые навыки
    val keySkills: String,

    // Контакты
    val contacts: String,

    // Ссылка на сайт HH с вакансией
    val alternateUrl: String,

    )
