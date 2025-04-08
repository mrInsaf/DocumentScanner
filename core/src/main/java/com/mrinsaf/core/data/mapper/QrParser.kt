package com.mrinsaf.core.data.mapper

import com.mrinsaf.core.domain.model.QrDocumentDetails
import kotlinx.serialization.json.Json

object QrParser {
    private val jsonFormat = Json

    fun parse(rawJson: String): QrDocumentDetails {
        return try {
            jsonFormat.decodeFromString<QrDocumentDetails>(rawJson)
        } catch (e: Exception) {
            println(e)
            throw e
        }
    }
}