package ru.practicum.android.diploma.search.data.mapper

import ru.practicum.android.diploma.data.dto.responses.areas.AreaUnitDAO
import ru.practicum.android.diploma.search.domain.model.fields.Area

class AreaMapper {
    /**
     * HH криво присылает поле parent - перезаписывание по ходу итерации
     */
    private fun recursiveFlatmap(
        src: List<AreaUnitDAO>,
        limit: Int,
        parent: String? = null
    ): List<AreaUnitDAO> {
        val result = mutableListOf<AreaUnitDAO>()
        src.forEach {
            result.add(it.copy(children = arrayListOf(), parent = parent))
            if (limit > 0 && it.children != null) {
                result.addAll(
                    recursiveFlatmap(it.children, limit - 1, it.id)
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
    fun map(src: List<AreaUnitDAO>, recursiveLimit: Int = 0): List<Area> {
        val recursiveMap = recursiveFlatmap(src, recursiveLimit)
        return recursiveMap.map { dto ->
            Area(
                dto.id.toInt(),
                dto.name,
                dto.parent
            )
        }
    }
}
