package com.wei.traveltaoyuanlite.core.data.di

import com.wei.traveltaoyuanlite.core.data.utils.ConnectivityManagerNetworkMonitor
import com.wei.traveltaoyuanlite.core.data.utils.NetworkMonitor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsNetworkMonitor(
        networkMonitor: ConnectivityManagerNetworkMonitor,
    ): NetworkMonitor
}
