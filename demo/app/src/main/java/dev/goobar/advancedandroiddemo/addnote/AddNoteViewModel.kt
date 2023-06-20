package dev.goobar.advancedandroiddemo.addnote

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModel @Inject constructor() : ViewModel() {

    private val _events: MutableSharedFlow<Event> = MutableSharedFlow()
    val events = _events.asSharedFlow()

    var title by mutableStateOf("")
        private set
    var content by mutableStateOf("")
        private set
    val categories by mutableStateOf(listOf("Android", "Kotlin", "Software Development", "Cloud"))
    var selectedCategory by mutableStateOf(categories.first())
        private set

    val showSaveButton: StateFlow<Boolean> =
        snapshotFlow { title }.combine(snapshotFlow { content }) { title, content ->
            title.isNotBlank() && content.isNotBlank()
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), false)

    fun onTitleChanged(newTitle: String) { title = newTitle }
    fun onContentChanged(newContent: String) { content = newContent }
    fun onSaveClicked() {
        viewModelScope.launch {
            _events.emit(Event.SaveCompleted)
            TODO("Save the note to database")
        }
    }
    fun onCategoryClicked(newCategory: String) {
        viewModelScope.launch { selectedCategory = newCategory }
    }

    sealed class Event {
        object SaveCompleted : Event()
    }
}