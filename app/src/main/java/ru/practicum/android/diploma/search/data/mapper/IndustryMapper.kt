package ru.practicum.android.diploma.search.data.mapper

import ru.practicum.android.diploma.data.dto.responses.industry.IndustryUnitDAO
import ru.practicum.android.diploma.search.domain.model.fields.Area
import ru.practicum.android.diploma.search.domain.model.fields.Industry

class IndustryMapper {
    private fun recursiveMap(
        src: List<IndustryUnitDAO>,
        limit: Int,
        parent: String? = null
    ): List<IndustryUnitDAO> {
        val result = mutableListOf<IndustryUnitDAO>()
        src.forEach {
            if (parent == null)
                println()
            result.add(
                it.copy(parent = parent)
            )
            if (limit > 0 && it.industries != null) {
                result.addAll(
                    recursiveMap(it.industries, limit - 1, it.id)
                )
            }
        }
        return result
    }

    /**
     * @param recursiveLimit отвечает за глубину прохода по входным area.
     * При значении = 0, преобразует только верхний слой списка DTO. При значении 1 - выдаст входной список DTO,
     * а так-же детей. 2 - входной, дети, и дети детей. И так далее
     */
    fun map(
        src: List<IndustryUnitDAO>,
        recursiveLimit: Int = 0
    ): List<Industry> {
        val recursiveMap = recursiveMap(src, recursiveLimit)
        return recursiveMap.map { dto ->
            Industry(
                dto.id.toInt(),
                dto.name,
                dto.parent?.toInt()
            )
        }
    }
}
