package com.mrinsaf.feature_document_details.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrinsaf.core.data.mapper.DocumentMapper
import com.mrinsaf.core.data.model.api.request.DocumentInfoRequest
import com.mrinsaf.core.data.model.api.response.DocumentInfoResponse
import com.mrinsaf.core.data.repository.PdfConverterRepository
import com.mrinsaf.core.domain.model.QrDocumentDetails
import com.mrinsaf.core.domain.repository.DocumentRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class DocumentDetailsViewModel(
    private val documentRepository: DocumentRepository,
    private val pdfConverterRepository: PdfConverterRepository,
    private val mapper: DocumentMapper = DocumentMapper
): ViewModel() {

    var documentInfo = MutableStateFlow<DocumentInfoResponse?>(null)
        private set

    var pdfBytes = MutableStateFlow<ByteArray?>(null)
        private set

    fun onReviewDocumentClick(qrDocumentDetails: QrDocumentDetails) = viewModelScope.launch {
        handleDocumentConversion(qrDocumentDetails)
    }

    private suspend fun handleDocumentConversion(qrDocumentDetails: QrDocumentDetails) {
        try {
            val docxBytes = loadOriginalDocument(qrDocumentDetails)
            val pdfBytes = convertDocumentToPdf(docxBytes)
            updatePdfState(pdfBytes)
        } catch (e: Exception) {
            println(e)
        }
    }

    private suspend fun loadOriginalDocument(qrDocumentDetails: QrDocumentDetails): ByteArray {
        val downloadRequest = mapper.mapQrDetailsToDownloadRequest(qrDocumentDetails)
        return documentRepository.downloadDocument(downloadRequest)
    }

    private suspend fun convertDocumentToPdf(docxBytes: ByteArray): ByteArray {
        return pdfConverterRepository.convertDocxToPdf(docxBytes)
    }

    private fun updatePdfState(pdfBytesResponse: ByteArray) {
        pdfBytes.value = pdfBytesResponse
    }    fun getDocumentInformation(
        kksCode: String,
        versionPrefix: String,
        version: Int,
    ) {
        viewModelScope.launch {
            val documentInformation = documentRepository.getDocumentInfo(
                DocumentInfoRequest(
                    kksCode = kksCode,
                    versionPrefix = versionPrefix,
                    version = version
                )
            )
            documentInfo.value = documentInformation
        }
    }
}