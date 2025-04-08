package com.mrinsaf.core.domain.repository.local

import com.mrinsaf.core.domain.model.DocType

interface DocTypeDataSource {
    fun getDocType(code: String): DocType?
}