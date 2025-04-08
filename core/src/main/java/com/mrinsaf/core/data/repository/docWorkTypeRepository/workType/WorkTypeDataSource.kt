package com.mrinsaf.core.data.repository.docWorkTypeRepository.workType

import com.mrinsaf.core.data.model.WorkType

interface WorkTypeDataSource {
    fun getWorkType(code: String): WorkType?
}