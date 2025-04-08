package com.mrinsaf.core.data.repository.docWorkTypeRepository.docType

import com.mrinsaf.core.data.model.DocType

object LocalDocTypeDataSource : DocTypeDataSource {
    private val map = mapOf(
        "AA" to DocType("AA", "Акт измерений"),
        // ... все остальные пары из docType.txt
    )

    override fun getDocType(code: String) = map[code]
}