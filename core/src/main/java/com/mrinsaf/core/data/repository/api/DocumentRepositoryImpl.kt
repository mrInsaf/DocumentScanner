package com.mrinsaf.core.data.repository.api

import com.mrinsaf.core.data.dataSource.api.DocumentApiService
import com.mrinsaf.core.domain.model.api.request.DocumentDownloadRequest
import com.mrinsaf.core.domain.model.api.request.DocumentInfoRequest
import com.mrinsaf.core.domain.model.api.response.DocumentInfoResponse
import com.mrinsaf.core.domain.repository.api.DocumentRepository

class DocumentRepositoryImpl(
    private val apiService: DocumentApiService,
) : DocumentRepository {
    override suspend fun getDocumentInfo(params: DocumentInfoRequest): DocumentInfoResponse {
        return apiService.getDocumentInfo(
            personCode = params.personCode,
            kksCode = params.kksCode,
            workType = params.workType,
            docType = params.docType,
            versionPrefix = params.versionPrefix,
            version = params.version,
            dateInput = params.dateInput,
            dateCreate = params.dateCreate
        )
    }

    override suspend fun downloadDocument(params: DocumentDownloadRequest): ByteArray {
        val response = apiService.downloadDocument(
            kksCode = params.kksCode,
            versionPrefix = params.versionPrefix,
            version = params.version
        )
        return response.body()?.bytes() ?: throw Exception("Download failed")
    }
}