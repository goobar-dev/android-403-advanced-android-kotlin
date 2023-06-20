package dev.goobar.advancedandroiddemo.analytics

interface Logger {
    fun log(eventName: String, properties: Map<String, Any> = emptyMap())
}