@file:OptIn(ExperimentalMaterial3Api::class)

package dev.goobar.advancedandroiddemo.addnote

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.goobar.advancedandroiddemo.R
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Composable
fun AddNoteScreen(
    viewModel: AddNoteViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {

    val layoutDirection = LocalLayoutDirection.current
    val showSaveButton by viewModel.showSaveButton.collectAsState()
    val foo by remember { derivedStateOf { showSaveButton == true } }

    LaunchedEffect(Unit) {
        viewModel.events.collect {
            when (it) {
                AddNoteViewModel.Event.SaveCompleted -> onBackClick()
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add Note") },
                navigationIcon = { IconButton(onBackClick) { Icon(painterResource(id = R.drawable.ic_arrow_back), contentDescription = "Back")} }
            )
         },
        floatingActionButton = {
            Box(modifier = Modifier.size(56.dp)) {
                AnimatedVisibility(visible = showSaveButton) {
                    FloatingActionButton(onClick = viewModel::onSaveClicked) {
                        Icon(painter = painterResource(R.drawable.ic_save), contentDescription = "Save Note")
                    }
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(
                    PaddingValues(
                        start = 20.dp + paddingValues.calculateStartPadding(layoutDirection),
                        top = 20.dp + paddingValues.calculateTopPadding(),
                        end = 20.dp + paddingValues.calculateEndPadding(layoutDirection),
                        bottom = 20.dp + paddingValues.calculateBottomPadding()
                    )
                )
                .fillMaxSize(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                value = viewModel.title,
                onValueChange = viewModel::onTitleChanged,
                label = { Text("Title")}
            )

            CategoryDropdown(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                viewModel.selectedCategory,
                viewModel.categories.toImmutableList(),
                viewModel::onCategoryClicked
            )

            OutlinedTextField(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                value = viewModel.content,
                onValueChange = viewModel::onContentChanged,
                singleLine = false,
                label = { Text("Content")}
            )
        }
    }
}

@Composable
private fun CategoryDropdown(
    modifier: Modifier = Modifier,
    selected: String,
    categories: ImmutableList<String>,
    onCategoryClicked: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    Box(modifier) {

        val source = remember { MutableInteractionSource() }
        val isPressed by source.collectIsPressedAsState()
        if (isPressed) expanded = true

        OutlinedTextField(
            interactionSource = source,
            modifier = Modifier
                .fillMaxWidth(1f)
                .clickable(onClick = { expanded = true }),
            readOnly = true,
            value = selected,
            onValueChange = {},
            label = { Text("Category") }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            categories.forEach { category ->
                DropdownMenuItem(
                    onClick = {
                        onCategoryClicked(category)
                        expanded = false
                    },
                    text = {
                        Text(category)
                    }
                )
            }
        }
    }
}