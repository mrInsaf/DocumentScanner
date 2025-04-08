package com.mrinsaf.core.data.repository

import com.mrinsaf.core.data.dataSource.LocalWorkTypeDataSource
import com.mrinsaf.core.data.repository.docDocTypeRepository.docType.LocalDocTypeDataSource
import com.mrinsaf.core.domain.model.DocType
import com.mrinsaf.core.domain.model.WorkType
import com.mrinsaf.core.domain.repository.DocTypeDataSource
import com.mrinsaf.core.domain.repository.WorkTypeDataSource

object DocWorkTypeRepository {

    private val workTypeDataSource: WorkTypeDataSource by lazy { LocalWorkTypeDataSource }
    private val docTypeDataSource: DocTypeDataSource by lazy { LocalDocTypeDataSource }

    fun getWorkType(code: String): WorkType? =
        workTypeDataSource.getWorkType(code)

    fun getDocType(code: String): DocType? =
        docTypeDataSource.getDocType(code)
}