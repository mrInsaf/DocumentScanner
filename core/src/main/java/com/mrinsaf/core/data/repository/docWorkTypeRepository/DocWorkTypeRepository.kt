package com.mrinsaf.core.data.repository.docWorkTypeRepository

import com.mrinsaf.core.data.model.DocType
import com.mrinsaf.core.data.model.WorkType
import com.mrinsaf.core.data.repository.docDocTypeRepository.docType.LocalDocTypeDataSource
import com.mrinsaf.core.data.repository.docWorkTypeRepository.docType.DocTypeDataSource
import com.mrinsaf.core.data.repository.docWorkTypeRepository.workType.LocalWorkTypeDataSource
import com.mrinsaf.core.data.repository.docWorkTypeRepository.workType.WorkTypeDataSource

object DocWorkTypeRepository {

    private val workTypeDataSource: WorkTypeDataSource by lazy { LocalWorkTypeDataSource }
    private val docTypeDataSource: DocTypeDataSource by lazy { LocalDocTypeDataSource }

    fun getWorkType(code: String): WorkType? =
        workTypeDataSource.getWorkType(code)

    fun getDocType(code: String): DocType? =
        docTypeDataSource.getDocType(code)
}