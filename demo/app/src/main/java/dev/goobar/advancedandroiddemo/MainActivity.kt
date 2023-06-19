@file:OptIn(ExperimentalMaterial3Api::class)

package dev.goobar.advancedandroiddemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import dev.goobar.advancedandroiddemo.ui.theme.AdvancedAndroidDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AdvancedAndroidDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainActivityContent()
                }
            }
        }
    }
}

@Composable
private fun MainActivityContent() {
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
                null -> AndroidVersionsList() { clickedInfo ->
                    selectedItem = clickedInfo
                }
                else -> AndroidVersionDetails(currentItem) {
                    selectedItem = null
                }
            }
        }
    }
}

@Composable
private fun AndroidVersionsList(onClick: (AndroidVersionInfo) -> Unit) {
    LazyColumn(
        contentPadding = PaddingValues(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(
            items = AndroidVersionsRepository.data,
            key = { info -> info.apiVersion }) { info ->
            AndroidVersionInfoCard(info, onClick)
        }
    }
}

@Composable
private fun AndroidVersionDetails(info: AndroidVersionInfo, onBack: () -> Unit) {
    BackHandler(enabled = true, onBack = onBack)
    Column(modifier = Modifier.padding(20.dp)) {
        Text(text = info.publicName, style = MaterialTheme.typography.displayMedium)
        Text(text = "${info.codename} - API ${info.apiVersion}", style = MaterialTheme.typography.headlineSmall)
        Text(
            modifier = Modifier.padding(top = 20.dp),
            text = info.details,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

    }
}

@Composable
private fun AndroidVersionInfoCard(
    info: AndroidVersionInfo,
    onClick: (AndroidVersionInfo) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 120.dp)
            .clickable { onClick(info) }
    ) {
        Row(
            modifier = Modifier.padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.size(64.dp),
                painter = painterResource(id = R.drawable.ic_android),
                contentDescription = "Android icon"
            )
            Column(modifier = Modifier.padding(start = 20.dp)) {
                Text(text = info.publicName, style = MaterialTheme.typography.headlineMedium)
                Text(text = "${info.codename} - API ${info.apiVersion}")
                Text(
                    modifier = Modifier.padding(top = 4.dp),
                    text = info.details,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}