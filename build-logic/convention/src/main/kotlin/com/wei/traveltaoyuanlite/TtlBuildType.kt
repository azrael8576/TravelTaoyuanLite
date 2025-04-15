package com.wei.traveltaoyuanlite

/**
 * This is shared between :app module to provide configurations type safety.
 */
enum class TtlBuildType(val applicationIdSuffix: String? = null) {
    DEBUG(".debug"),
    RELEASE
}
