package com.mrinsaf.core.data.mapper

import com.mrinsaf.core.domain.model.DocumentDetails
import kotlinx.serialization.json.Json

object QrParser {
    private val jsonFormat = Json

    fun parse(rawJson: String): DocumentDetails {
        return try {
            jsonFormat.decodeFromString<DocumentDetails>(rawJson)
        } catch (e: Exception) {
            println(e)
            throw e
        }
    }
}