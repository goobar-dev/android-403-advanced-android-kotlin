package dev.goobar.advancedandroiddemo.data

import kotlinx.serialization.Serializable

@Serializable
data class Topic(
    val title: String,
    val categories: List<String>,
    val content: String
)