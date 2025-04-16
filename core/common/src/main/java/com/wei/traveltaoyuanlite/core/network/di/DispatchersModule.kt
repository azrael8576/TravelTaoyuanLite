package com.wei.traveltaoyuanlite.core.network.di

import com.wei.traveltaoyuanlite.core.network.Dispatcher
import com.wei.traveltaoyuanlite.core.network.TtlDispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {
    /**
     * 提供 IO CoroutineDispatcher 的實例，可以在需要進行 IO 操作的協程中使用。
     *
     * @return IO CoroutineDispatcher 的實例
     */
    @Provides
    @Dispatcher(TtlDispatchers.IO)
    fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    /**
     * 提供 Default CoroutineDispatcher 的實例，可以在需要進行 CPU 密集型工作的協程中使用。
     *
     * @return Default CoroutineDispatcher 的實例
     */
    @Provides
    @Dispatcher(TtlDispatchers.Default)
    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default
}
