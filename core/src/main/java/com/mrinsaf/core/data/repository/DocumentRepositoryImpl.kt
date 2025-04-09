package com.mrinsaf.core.data.repository

import com.mrinsaf.core.data.dataSource.api.DocumentApiService
import com.mrinsaf.core.data.model.api.request.DocumentDownloadRequest
import com.mrinsaf.core.data.model.api.request.DocumentInfoRequest
import com.mrinsaf.core.data.model.api.response.DocumentInfoResponse
import com.mrinsaf.core.domain.repository.DocumentRepository
import okhttp3.ResponseBody
import retrofit2.Response

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

    override suspend fun register(login: String, password: String): String {
        val response = apiService.register(login, password)
        return handleAuthResponse(response)
    }

    override suspend fun login(login: String, password: String): String {
        val response = apiService.login(login, password)
        return handleAuthResponse(response)
    }

    override fun convertDocxToPdf(file: ByteArray): ByteArray {
        TODO("Not yet implemented")
    }

    private fun handleAuthResponse(response: Response<ResponseBody>): String {
        return if (response.isSuccessful) {
            response.body()?.string() ?: "Success"
        } else {
            throw Exception(response.errorBody()?.string() ?: "Error")
        }
    }
}