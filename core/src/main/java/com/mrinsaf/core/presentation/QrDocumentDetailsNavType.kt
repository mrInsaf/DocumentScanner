package com.mrinsaf.core.presentation

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import com.mrinsaf.core.domain.model.QrDocumentDetails
import kotlinx.serialization.json.Json

object QrDocumentDetailsNavType : NavType<QrDocumentDetails>(isNullableAllowed = false) {

    // Преобразование объекта в Bundle
    override fun put(bundle: Bundle, key: String, value: QrDocumentDetails) {
        // Сериализуем объект в JSON (или другой формат)
        val json = Json.Default.encodeToString(value)
        bundle.putString(key, json)
    }

    override fun get(bundle: Bundle, key: String): QrDocumentDetails? {
        val json = bundle.getString(key) ?: return null
        return Json.Default.decodeFromString(json)
    }

    override fun parseValue(value: String): QrDocumentDetails {
        return Json.Default.decodeFromString(Uri.decode(value))
    }

    override fun serializeAsValue(value: QrDocumentDetails): String {
        return Uri.encode(Json.Default.encodeToString(value))
    }

    // Уникальное имя типа (для внутреннего использования)
    override val name = "DocumentDetails"
}