package com.mrinsaf.core.domain.model

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import kotlinx.serialization.json.Json

object QrDocumentDetailsNavType : NavType<QrDocumentDetails>(isNullableAllowed = false) {

    // Преобразование объекта в Bundle
    override fun put(bundle: Bundle, key: String, value: QrDocumentDetails) {
        // Сериализуем объект в JSON (или другой формат)
        val json = Json.encodeToString(value)
        bundle.putString(key, json)
    }

    override fun get(bundle: Bundle, key: String): QrDocumentDetails? {
        val json = bundle.getString(key) ?: return null
        return Json.decodeFromString(json)
    }

    override fun parseValue(value: String): QrDocumentDetails {
        return Json.decodeFromString(Uri.decode(value))
    }

    override fun serializeAsValue(value: QrDocumentDetails): String {
        return Uri.encode(Json.encodeToString(value))
    }

    // Уникальное имя типа (для внутреннего использования)
    override val name = "DocumentDetails"
}