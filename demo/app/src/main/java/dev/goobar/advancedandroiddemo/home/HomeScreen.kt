@file:OptIn(ExperimentalMaterial3Api::class)

package dev.goobar.advancedandroiddemo.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dev.goobar.advancedandroiddemo.R
import dev.goobar.advancedandroiddemo.data.AndroidVersionInfo
import dev.goobar.advancedandroiddemo.details.AndroidVersionDetailsScreen
import dev.goobar.advancedandroiddemo.versions.AndroidVersionsListScreen

@Composable
internal fun MainActivityContent() {
    val snackbarHostState = remember { SnackbarHostState() }
    var selectedItem by rememberSaveable { mutableStateOf<AndroidVersionInfo?>(null) }

    Scaffold(
        topBar = {
            when (val currentItem = selectedItem) {
                null -> {
                    TopAppBar(title = { Text("Hello Advanced Android") })
                }

                else -> {
                    TopAppBar(
                        title = {
                            Text(
                                text = currentItem.publicName,
                                modifier = Modifier.padding(start = 20.dp)
                            )
                        },
                        navigationIcon = {
                            IconButton(onClick = { selectedItem = null }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_arrow_back),
                                    contentDescription = "Back arrow"
                                )
                            }
                        }
                    )
                }
            }
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {

            when (val currentItem = selectedItem) {
                null -> AndroidVersionsListScreen() { clickedInfo ->
                    selectedItem = clickedInfo
                }

                else -> AndroidVersionDetailsScreen(currentItem) {
                    selectedItem = null
                }
            }
        }
    }
}