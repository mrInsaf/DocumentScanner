package com.mrinsaf.core.data.model

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

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
