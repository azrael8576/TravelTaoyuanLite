package com.wei.traveltaoyuanlite.core.network

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val ttlDispatcher: TtlDispatchers)

enum class TtlDispatchers {
    Default,
    IO,
}
