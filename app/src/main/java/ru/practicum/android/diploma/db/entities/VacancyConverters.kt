package ru.practicum.android.diploma.db.entities

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.practicum.android.diploma.search.domain.model.fields.Address
import ru.practicum.android.diploma.search.domain.model.fields.Contacts
import ru.practicum.android.diploma.search.domain.model.fields.Employer
import ru.practicum.android.diploma.search.domain.model.fields.Employment
import ru.practicum.android.diploma.search.domain.model.fields.Experience
import ru.practicum.android.diploma.search.domain.model.fields.KeySkill
import ru.practicum.android.diploma.search.domain.model.fields.Phone
import ru.practicum.android.diploma.search.domain.model.fields.Schedule
import ru.practicum.android.diploma.search.domain.model.fields.VacancyArea

class VacancyConverters {
    @TypeConverter
    fun fromAddress(address: Address): String {
        return Gson().toJson(address)
    }

    @TypeConverter
    fun toAddress(data: String): Address {
        return Gson().fromJson(data, Address::class.java)
    }

    @TypeConverter
    fun fromContacts(contacts: Contacts): String {
        return Gson().toJson(contacts)
    }

    @TypeConverter
    fun toContacts(data: String): Contacts {
        return Gson().fromJson(data, Contacts::class.java)
    }

    @TypeConverter
    fun toArea(data: String): VacancyArea {
        return Gson().fromJson(data, VacancyArea::class.java)
    }

    @TypeConverter
    fun fromArea(area: VacancyArea): String {
        return Gson().toJson(area)
    }

    @TypeConverter
    fun fromEmployer(employer: Employer): String {
        return Gson().toJson(employer)
    }

    @TypeConverter
    fun toEmployer(data: String): Employer {
        return Gson().fromJson(data, Employer::class.java)
    }

    @TypeConverter
    fun toEmployment(data: String): Employment {
        return Gson().fromJson(data, Employment::class.java)
    }

    @TypeConverter
    fun fromEmployment(employment: Employment): String {
        return Gson().toJson(employment)
    }

    @TypeConverter
    fun toExperience(data: String): Experience {
        return Gson().fromJson(data, Experience::class.java)
    }

    @TypeConverter
    fun fromExperience(experience: Experience): String {
        return Gson().toJson(experience)
    }

    @TypeConverter
    fun fromPhoneList(phones: List<Phone?>): String {
        val type = object : TypeToken<List<Phone?>>() {}.type
        return Gson().toJson(phones, type)
    }

    @TypeConverter
    fun toPhoneList(data: String): List<Phone?> {
        val type = object : TypeToken<List<Phone?>>() {}.type
        return Gson().fromJson(data, type)
    }
    @TypeConverter
    fun fromKeySkills(keySkills: List<KeySkill?>): String {
        val type = object : TypeToken<List<KeySkill?>>() {}.type
        return Gson().toJson(keySkills, type)
    }
    @TypeConverter
    fun toKeySkills(data: String): List<KeySkill?> {
        val type = object : TypeToken<List<KeySkill?>>() {}.type
        return Gson().fromJson(data, type)
    }
    @TypeConverter
    fun toSchedule(data: String): Schedule {
        return Gson().fromJson(data, Schedule::class.java)
    }

    @TypeConverter
    fun fromSchedule(shedule: Schedule): String {
        return Gson().toJson(shedule)
    }


}
