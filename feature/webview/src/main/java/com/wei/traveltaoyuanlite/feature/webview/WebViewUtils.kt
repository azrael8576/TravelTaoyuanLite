package com.wei.traveltaoyuanlite.feature.webview

import androidx.core.net.toUri
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

// 檢查 URL 是否包含協議 (http:// 或 https://)，並添加缺少的協議
fun formatUrl(url: String): String {
    var decodedUrl = URLDecoder.decode(url, StandardCharsets.UTF_8.toString())

    if (!decodedUrl.startsWith("http://") && !decodedUrl.startsWith("https://")) {
        decodedUrl = "https://$decodedUrl" // 預設補充 https
    }

    return decodedUrl
}

// 檢查 URL 是否來自信任的 domain
fun isTrustedDomain(url: String, trustedDomains: List<String>): Boolean {
    val uri = url.toUri()
    return trustedDomains.any { uri.host?.contains(it) == true }
}
