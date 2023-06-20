package dev.goobar.advancedandroiddemo.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dev.goobar.advancedandroiddemo.analytics.FirebaseAnalyticsLogger
import dev.goobar.advancedandroiddemo.analytics.Logger

@Module
@InstallIn(ViewModelComponent::class)
abstract class AnalyticsModule {

    @Binds
    abstract fun bindLogger(loggerImpl: FirebaseAnalyticsLogger): Logger
}