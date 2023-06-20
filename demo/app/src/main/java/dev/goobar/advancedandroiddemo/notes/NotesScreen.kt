@file:OptIn(ExperimentalMaterial3Api::class)

package dev.goobar.advancedandroiddemo.notes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.goobar.advancedandroiddemo.R
import dev.goobar.advancedandroiddemo.design.LoadingCard

@Composable
fun NotesScreen(
    viewModel: NotesViewModel = hiltViewModel(),
    onAddNoteClick: () -> Unit
) {
    val notes by viewModel.notes.collectAsState()
    val layoutDirection = LocalLayoutDirection.current

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Hello Advanced Android") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddNoteClick) {
                Icon(painter = painterResource(R.drawable.ic_add_note), contentDescription = "Add Note")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(1f),
            contentPadding = PaddingValues(
                start = 20.dp + paddingValues.calculateStartPadding(layoutDirection),
                top = 20.dp + paddingValues.calculateTopPadding(),
                end = 20.dp + paddingValues.calculateEndPadding(layoutDirection),
                bottom = 20.dp + paddingValues.calculateBottomPadding()
            ),
            verticalArrangement = Arrangement.Absolute.spacedBy(16.dp)
        ) {
            if (notes.isEmpty()) {
                items(10) { LoadingCard() }
            } else {
                items(notes, key = { it.id }) { note ->
                    NoteCard(note = note, viewModel::onNoteClicked)
                }
            }
        }
    }
}

@Composable
private fun NoteCard(note: NotesViewModel.NoteViewItem, onClick: (NotesViewModel.NoteViewItem) -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(1f),
        onClick = { onClick(note) }
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(text = note.title, style = MaterialTheme.typography.displaySmall)
            Text(text = note.category, style = MaterialTheme.typography.labelMedium)
            Spacer(modifier = Modifier.size(8.dp))
            Text(text = note.content, style = MaterialTheme.typography.labelLarge)
        }
    }
}