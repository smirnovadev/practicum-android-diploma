package ru.practicum.android.diploma.filters.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.practicum.android.diploma.data.dto.NetworkResponse
import ru.practicum.android.diploma.data.dto.requests.AreaRequest
import ru.practicum.android.diploma.data.dto.requests.IndustriesRequest
import ru.practicum.android.diploma.data.network.NetworkClient
import ru.practicum.android.diploma.db.AppDatabase
import ru.practicum.android.diploma.db.entities.AreaEntity
import ru.practicum.android.diploma.db.entities.IndustryEntity
import ru.practicum.android.diploma.filters.domain.FiltersRepository
import ru.practicum.android.diploma.search.domain.model.fields.Area
import ru.practicum.android.diploma.search.domain.model.fields.Industry

class FiltersRepositoryImpl(
    private val networkClient: NetworkClient,
    private val appDatabase: AppDatabase,
) : FiltersRepository {
    override suspend fun insertIndustry(industry: Industry) {
        appDatabase.industriesDao().insertIndustry(
            IndustryEntity(
                industry.id,
                industry.name,
                industry.parent
            )
        )
    }

    override suspend fun insertIndustries(industries: List<Industry>) {
        appDatabase.industriesDao().insertIndustries(
            industries.map {
                IndustryEntity(
                    it.id,
                    it.name,
                    it.parent
                )
            }
        )
    }

    override suspend fun deleteIndustry(industry: Industry) {
        appDatabase.industriesDao().deleteIndustry(
            IndustryEntity(
                industry.id,
                industry.name,
                industry.parent
            )
        )
    }

    override fun getIndustry(): Flow<List<Industry>> = flow {
        val industries = appDatabase.industriesDao().getIndustries()
        emit(industries.map {
            Industry(
                it.id,
                it.name,
                it.parent
            )
        })
    }

    override suspend fun insertArea(area: Area) {
        appDatabase.areasDao().insertArea(
            AreaEntity(
                area.id,
                area.name,
                area.url,
                area.parent
            )
        )
    }

    override suspend fun insertAreas(area: List<Area>) {
        appDatabase.areasDao().insertAreas(area.map {
            AreaEntity(
                it.id,
                it.name,
                it.url,
                it.parent
            )
        })
    }

    override suspend fun deleteArea(area: Area) {
        appDatabase.areasDao().deleteArea(
            AreaEntity(
                area.id,
                area.name,
                area.url,
                area.parent
            )
        )
    }

    override fun getArea(): Flow<List<Area>> = flow {
        val areas = appDatabase.areasDao().getAreas()
        emit(areas.map {
            Area(
                it.id,
                it.name,
                it.url,
                it.parent
            )
        })
    }

    override fun getCountries(): Flow<List<Area>> = flow {
        emit(appDatabase.areasDao().getCountries().map {
            Area(
                it.id,
                it.name,
                it.url,
                it.parent
            )
        })
    }

    override fun getRegions(): Flow<List<Area>> = flow {
        emit(appDatabase.areasDao().getRegions().map {
            Area(
                it.id,
                it.name,
                it.url,
                it.parent
            )
        })
    }

    override suspend fun downloadAreas(): Flow<NetworkResponse> = flow {
        emit(
            networkClient.getAreas(AreaRequest())
        )
    }

    override suspend fun downloadIndustries(): Flow<NetworkResponse> = flow {
        emit(
            networkClient.getIndustries(IndustriesRequest())
        )
    }

}
