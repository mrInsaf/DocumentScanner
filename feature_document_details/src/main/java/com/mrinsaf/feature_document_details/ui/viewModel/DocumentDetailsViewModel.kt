package com.mrinsaf.feature_document_details.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.mrinsaf.core.data.mapper.DocumentMapper
import com.mrinsaf.core.data.model.api.request.DocumentInfoRequest
import com.mrinsaf.core.data.model.api.response.DocumentInfoResponse
import com.mrinsaf.core.data.repository.PdfConverterRepository
import com.mrinsaf.core.domain.model.QrDocumentDetails
import com.mrinsaf.core.domain.model.ScreenDestination
import com.mrinsaf.core.domain.repository.DocumentRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class DocumentDetailsViewModel(
    private val documentRepository: DocumentRepository,
    private val pdfConverterRepository: PdfConverterRepository,
    private val mapper: DocumentMapper = DocumentMapper,
): ViewModel() {

    var documentInfo = MutableStateFlow<DocumentInfoResponse?>(null)
        private set

    var pdfBytes = MutableStateFlow<ByteArray?>(null)
        private set

    var pdfEvents = MutableSharedFlow<PdfEvent>(extraBufferCapacity = 1)
        private set

    sealed class PdfEvent {
        data class Success(val bytes: ByteArray) : PdfEvent()
        data class Error(val message: String) : PdfEvent()
    }

    suspend fun onReviewDocumentClick(
        qrDocumentDetails: QrDocumentDetails,
    ) {
        println("onReviewDocumentClick: Начало обработки документа")
        handleDocumentConversion(qrDocumentDetails)
        println("onReviewDocumentClick: Завершение обработки документа")
    }

    private suspend fun handleDocumentConversion(qrDocumentDetails: QrDocumentDetails) {
        println("handleDocumentConversion: Начало конвертации документа")
        try {
            println("handleDocumentConversion: Загрузка оригинального документа")
            val docxBytes = loadOriginalDocument(qrDocumentDetails)
            println("handleDocumentConversion: Оригинальный документ загружен, размер: ${docxBytes.size} байт")

            println("handleDocumentConversion: Конвертация документа в PDF")
            val pdfBytes = convertDocumentToPdf(docxBytes)
            println("handleDocumentConversion: Документ успешно сконвертирован в PDF, размер: ${pdfBytes.size} байт")

            println("handleDocumentConversion: Обновление состояния PDF")
            updatePdfState(pdfBytes)
            println("handleDocumentConversion: Состояние PDF обновлено")

            pdfEvents.emit(PdfEvent.Success(pdfBytes))
        } catch (e: Exception) {
            println("handleDocumentConversion: Произошла ошибка: ${e.message}")
            e.printStackTrace()
            pdfEvents.emit(PdfEvent.Error(e.message ?: "Unknown error"))
        }
    }

    private suspend fun loadOriginalDocument(qrDocumentDetails: QrDocumentDetails): ByteArray {
        println("loadOriginalDocument: Начало загрузки документа")
        val downloadRequest = mapper.mapQrDetailsToDownloadRequest(qrDocumentDetails)
        println("loadOriginalDocument: Создан запрос на загрузку: $downloadRequest")
        val documentBytes = documentRepository.downloadDocument(downloadRequest)
        println("loadOriginalDocument: Документ загружен, размер: ${documentBytes.size} байт")
        return documentBytes
    }

    private suspend fun convertDocumentToPdf(docxBytes: ByteArray): ByteArray {
        println("convertDocumentToPdf: Начало конвертации DOCX в PDF")
        val pdfBytes = pdfConverterRepository.convertDocxToPdf(docxBytes)
        println("convertDocumentToPdf: Конвертация завершена, размер PDF: ${pdfBytes.size} байт")
        return pdfBytes
    }

    private fun updatePdfState(pdfBytesResponse: ByteArray) {
        println("updatePdfState: Обновление состояния PDF")
        pdfBytes.value = pdfBytesResponse
        println("updatePdfState: Состояние PDF успешно обновленоЖ $pdfBytesResponse")
    }

    fun getDocumentInformation(
        kksCode: String,
        versionPrefix: String,
        version: Int,
    ) {
        println("getDocumentInformation: Начало получения информации о документе")
        viewModelScope.launch {
            try {
                println("getDocumentInformation: Создание запроса на получение информации о документе")
                val documentInformation = documentRepository.getDocumentInfo(
                    DocumentInfoRequest(
                        kksCode = kksCode,
                        versionPrefix = versionPrefix,
                        version = version
                    )
                )
                println("getDocumentInformation: Информация о документе получена: $documentInformation")
                documentInfo.value = documentInformation
            } catch (e: Exception) {
                println("getDocumentInformation: Произошла ошибка: ${e.message}")
                e.printStackTrace()
            }
        }
        println("getDocumentInformation: Завершение процесса получения информации о документе")
    }
}