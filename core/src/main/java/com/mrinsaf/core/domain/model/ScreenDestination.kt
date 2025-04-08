package com.mrinsaf.core.domain.model

import kotlinx.serialization.Serializable

sealed class ScreenDestination() {

    @Serializable
    object Scanner: ScreenDestination()

    @Serializable
    data class DocumentDetailsDestination(
        val data: DocumentDetails
    ): ScreenDestination()

    @Serializable
    data class DocumentReader(val documentTitle: String): ScreenDestination()
}
