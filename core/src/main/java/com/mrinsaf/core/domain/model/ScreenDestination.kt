package com.mrinsaf.core.domain.model

import kotlinx.serialization.Serializable

sealed class ScreenDestination() {

    @Serializable
    object ScannerDestination: ScreenDestination()

    @Serializable
    data class DocumentDetailsDestination(
        val data: QrDocumentDetails
    ): ScreenDestination()

    @Serializable
    data class DocumentReaderDestination(
        val pdfByteArray: ByteArray
    ): ScreenDestination() {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as DocumentReaderDestination

            if (!pdfByteArray.contentEquals(other.pdfByteArray)) return false

            return true
        }

        override fun hashCode(): Int {
            return pdfByteArray.contentHashCode()
        }
    }
}
