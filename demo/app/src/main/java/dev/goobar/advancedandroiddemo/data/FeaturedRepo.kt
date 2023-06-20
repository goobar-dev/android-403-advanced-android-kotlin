package dev.goobar.advancedandroiddemo.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FeaturedRepo(
    val name: String,
    @SerialName("display_name")
    val displayName: String,
    @SerialName("short_description")
    val shortDescription: String,
    val description: String,
    @SerialName("created_by")
    val createdBy: String,
)

@Serializable
data class FeaturedRepoResponse(
    val items: List<FeaturedRepo>
)