package com.wei.traveltaoyuanlite.core.data.di

import com.wei.traveltaoyuanlite.core.data.repository.DefaultEventRepository
import com.wei.traveltaoyuanlite.core.data.repository.DefaultTravelRepository
import com.wei.traveltaoyuanlite.core.data.repository.EventRepository
import com.wei.traveltaoyuanlite.core.data.repository.TravelRepository
import com.wei.traveltaoyuanlite.core.data.utils.ConnectivityManagerNetworkMonitor
import com.wei.traveltaoyuanlite.core.data.utils.NetworkMonitor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    internal abstract fun bindsEventRepository(
        eventRepository: DefaultEventRepository,
    ): EventRepository

    @Binds
    internal abstract fun bindsTravelRepository(
        travelRepository: DefaultTravelRepository,
    ): TravelRepository

    @Binds
    internal abstract fun bindsNetworkMonitor(
        networkMonitor: ConnectivityManagerNetworkMonitor,
    ): NetworkMonitor
}
