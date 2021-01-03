package com.nourtayeb.dindinn.common.di

import com.nourtayeb.dindinn.data.networking.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

@Module
@InstallIn(ApplicationComponent::class)
object RxSchedulers {


    @Provides
    fun provideScheduler(): Scheduler {
        return Schedulers.io()
    }
}