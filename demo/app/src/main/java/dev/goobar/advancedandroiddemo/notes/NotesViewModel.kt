package dev.goobar.advancedandroiddemo.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.goobar.advancedandroiddemo.data.NoteEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor() : ViewModel() {

    val notes: StateFlow<List<NoteViewItem>> = flowOf<List<NoteEntity>>()
        .map { notes -> notes.map { note -> note.toViewItem() } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList<NoteViewItem>())

    fun onNoteClicked(note: NoteViewItem) {
        TODO()
    }

    data class NoteViewItem(
        val id: Int,
        val title: String,
        val category: String,
        val content: String,
    )

    private fun NoteEntity.toViewItem() = NoteViewItem(0, "", "", "")
}