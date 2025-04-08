package com.mrinsaf.core.data.repository.docWorkTypeRepository.docType

import com.mrinsaf.core.data.model.DocType

interface DocTypeDataSource {
    fun getDocType(code: String): DocType?
}