package com.wei.traveltaoyuanlite.core

enum class AppLocale(val code: String, val text: String, val apiArg: String) {
    EN(code = "en", text = "English", apiArg = "en"),
    ZH_HANT_TW(code = "zh-Hant-TW", text = "中文(繁體)", apiArg = "zh-tw"),
    ZH_HANS(code = "zh-Hans", text = "简体中文", apiArg = "zh-cn"),
    JA(code = "ja", text = "Japanese", apiArg = "ja"),
    KO(code = "ko", text = "Korean", apiArg = "ko"),
}
