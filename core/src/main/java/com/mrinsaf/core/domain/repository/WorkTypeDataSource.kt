package com.mrinsaf.core.domain.repository

import com.mrinsaf.core.domain.model.WorkType

interface WorkTypeDataSource {
    fun getWorkType(code: String): WorkType?
}