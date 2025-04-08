package com.mrinsaf.core.data.repository.docWorkTypeRepository.workType

import com.mrinsaf.core.data.model.WorkType

object LocalWorkTypeDataSource : WorkTypeDataSource {
    private val map = mapOf(
        "000" to WorkType("000", "Общая часть..."),

    )

    override fun getWorkType(code: String) = map[code]
}