package com.wei.traveltaoyuanlite.feature.attractiondetail.navigation.navtype

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import com.wei.traveltaoyuanlite.core.data.navigation.AttractionDetailNavArgs
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

val AttractionDetailNavArgsType = object : NavType<AttractionDetailNavArgs>(
    isNullableAllowed = false,
) {
    override fun get(bundle: Bundle, key: String): AttractionDetailNavArgs? {
        return Json.decodeFromString(bundle.getString(key) ?: return null)
    }

    override fun parseValue(value: String): AttractionDetailNavArgs {
        return Json.decodeFromString(Uri.decode(value))
    }

    override fun serializeAsValue(value: AttractionDetailNavArgs): String {
        return Uri.encode(Json.encodeToString(value))
    }

    override fun put(bundle: Bundle, key: String, value: AttractionDetailNavArgs) {
        bundle.putString(key, Json.encodeToString(value))
    }
}
