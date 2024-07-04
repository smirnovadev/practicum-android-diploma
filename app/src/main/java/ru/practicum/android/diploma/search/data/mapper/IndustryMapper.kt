package ru.practicum.android.diploma.search.data.mapper

import ru.practicum.android.diploma.data.dto.responses.industry.IndustryUnitDTO
import ru.practicum.android.diploma.search.domain.model.fields.Industry

class IndustryMapper {
    private fun recursiveFlatmap(
        src: List<IndustryUnitDTO>,
        limit: Int,
        parent: String? = null
    ): List<IndustryUnitDTO> {
        val result = mutableListOf<IndustryUnitDTO>()
        src.forEach {
            result.add(it.copy(parent = parent))
            if (limit > 0 && it.industries != null) {
                result.addAll(
                    recursiveFlatmap(it.industries, limit - 1, it.id)
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
        src: List<IndustryUnitDTO>,
        recursiveLimit: Int = 0
    ): List<Industry> {
        val recursiveMap = recursiveFlatmap(src, recursiveLimit)
        return recursiveMap.map { dto ->
            Industry(
                dto.id.toInt(),
                dto.name,
                dto.parent?.toInt()
            )
        }
    }
}
