package com.wei.traveltaoyuanlite.core.decoder.di

import com.wei.traveltaoyuanlite.core.decoder.StringDecoder
import com.wei.traveltaoyuanlite.core.decoder.UriDecoder
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class StringDecoderModule {
    @Binds
    abstract fun bindStringDecoder(uriDecoder: UriDecoder): StringDecoder
}
