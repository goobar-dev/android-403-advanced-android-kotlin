package dev.goobar.advancedandroiddemo.topics

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.goobar.advancedandroiddemo.analytics.Logger
import dev.goobar.advancedandroiddemo.data.Topic
import dev.goobar.advancedandroiddemo.network.StudyGuideService
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import javax.inject.Inject

@HiltViewModel
class AndroidTopicsViewModel @Inject constructor(
    private val logger: Logger,
    private val service: StudyGuideService
    ) : ViewModel() {

    val topics = flow {
        emit(service.getTopics().map { it.toViewItem() })
    }.stateIn(viewModelScope + Dispatchers.IO, SharingStarted.WhileSubscribed(3000), emptyList())

    private val _events: MutableSharedFlow<Events> = MutableSharedFlow()
    val events = _events.asSharedFlow()

    fun onTopicClicked(topic: TopicViewItem) {
        logger.log("Topic Clicked", mapOf("title" to topic.title))
        viewModelScope.launch(Dispatchers.Main.immediate) { _events.emit(Events.ShowTopicClickedMessage("Clicked ${topic.title}")) }
    }

    sealed class Events {
        data class ShowTopicClickedMessage(val message: String): Events()
    }

    data class TopicViewItem(
        val title: String,
        val categories: ImmutableList<String>,
        val content: String
    )

    private fun Topic.toViewItem() = TopicViewItem(title, categories.toImmutableList(), content)
}