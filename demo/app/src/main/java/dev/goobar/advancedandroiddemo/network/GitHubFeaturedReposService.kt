package dev.goobar.advancedandroiddemo.network

import dev.goobar.advancedandroiddemo.data.FeaturedRepoResponse
import retrofit2.http.GET
import retrofit2.http.Headers

interface GitHubFeaturedReposService {

    @Headers("Accept: application/vnd.github+json")
    @GET("topics?q=Android+is:featured")
    suspend fun getFeaturedAndroidRepos(): FeaturedRepoResponse
}