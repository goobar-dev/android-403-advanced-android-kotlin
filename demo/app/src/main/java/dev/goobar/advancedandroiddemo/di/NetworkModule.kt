package dev.goobar.advancedandroiddemo.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.goobar.advancedandroiddemo.BuildConfig
import dev.goobar.advancedandroiddemo.network.StudyGuideService
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideStudyGuideService(): StudyGuideService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.STUDY_GUIDE_SERVICE_URL)
            .addConverterFactory(Json.asConverterFactory(MediaType.get("application/json")))
            .build()
            .create(StudyGuideService::class.java)
    }
}