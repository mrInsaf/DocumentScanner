package com.mrinsaf.core.presentation

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import com.mrinsaf.core.domain.model.QrDocumentDetails
import kotlinx.serialization.json.Json

object ByteArrayNavType : NavType<ByteArray>(isNullableAllowed = false) {

    // Преобразование объекта в Bundle
    override fun put(bundle: Bundle, key: String, value: ByteArray) {
        val json = Json.encodeToString(value)
        bundle.putString(key, json)
    }

    override fun get(bundle: Bundle, key: String): ByteArray? {
        val json = bundle.getString(key) ?: return null
        return Json.decodeFromString(json)
    }

    override fun parseValue(value: String): ByteArray {
        return Json.decodeFromString(Uri.decode(value))
    }

    override fun serializeAsValue(value: ByteArray): String {
        return Uri.encode(Json.encodeToString(value))
    }
}