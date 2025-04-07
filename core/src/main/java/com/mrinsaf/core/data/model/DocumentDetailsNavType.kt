package com.mrinsaf.core.data.model

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import kotlinx.serialization.json.Json

object DocumentDetailsNavType : NavType<DocumentDetails>(isNullableAllowed = false) {

    // Преобразование объекта в Bundle
    override fun put(bundle: Bundle, key: String, value: DocumentDetails) {
        // Сериализуем объект в JSON (или другой формат)
        val json = Json.encodeToString(value)
        bundle.putString(key, json)
    }

    override fun get(bundle: Bundle, key: String): DocumentDetails? {
        val json = bundle.getString(key) ?: return null
        return Json.decodeFromString(json)
    }

    override fun parseValue(value: String): DocumentDetails {
        return Json.decodeFromString(Uri.decode(value))
    }

    override fun serializeAsValue(value: DocumentDetails): String {
        return Uri.encode(Json.encodeToString(value))
    }

    // Уникальное имя типа (для внутреннего использования)
    override val name = "DocumentDetails"
}