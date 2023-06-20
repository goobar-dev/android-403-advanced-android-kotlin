package dev.goobar.advancedandroiddemo.network

import dev.goobar.advancedandroiddemo.data.Topic
import retrofit2.http.GET

interface StudyGuideService {
    @GET("/topics")
    suspend fun getTopics(): List<Topic>
}