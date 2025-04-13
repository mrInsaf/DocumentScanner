package com.mrinsaf.core.data.repository

import com.mrinsaf.core.data.dataSource.api.PdfConverterApiService
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody

class PdfConverterRepository(
    private val apiService: PdfConverterApiService
) {
    suspend fun convertDocxToPdf(docxBytes: ByteArray): ByteArray {
        val requestBody = docxBytes.toRequestBody(
            "application/vnd.openxmlformats-officedocument.wordprocessingml.document".toMediaType()
        )
        val part = MultipartBody.Part.createFormData(
            "docx_file",
            "document.docx",
            requestBody
        )

        return apiService.convertDocxToPdf(part).body()?.bytes() ?: throw Exception("Download failed")
    }
}