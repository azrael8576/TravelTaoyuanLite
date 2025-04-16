package com.wei.traveltaoyuanlite.core.network.di

import com.wei.traveltaoyuanlite.core.network.TtlNetworkDataSource
import com.wei.traveltaoyuanlite.core.network.retrofit.RetrofitTtlNetwork
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface FlavoredNetworkModule {

    @Binds
    fun binds(implementation: RetrofitTtlNetwork): TtlNetworkDataSource
}
