@file:OptIn(ExperimentalMaterial3Api::class)

package dev.goobar.advancedandroiddemo.versions

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import dev.goobar.advancedandroiddemo.R
import dev.goobar.advancedandroiddemo.data.AndroidVersionInfo
import dev.goobar.advancedandroiddemo.data.AndroidVersionsRepository
import dev.goobar.advancedandroiddemo.ui.theme.AdvancedAndroidDemoTheme
import dev.goobar.advancedandroiddemo.ui.theme.custom.CustomButton
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.immutableListOf
import kotlinx.collections.immutable.toImmutableList

@Composable
internal fun AndroidVersionsListScreen(
    viewModel: AndroidVersionsListViewModel = viewModel(),
    onClick: (AndroidVersionInfo) -> Unit
) {
    val state by viewModel.state.collectAsState()
    val listState = state.versionsList

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Hello Advanced Android") },
                actions = {
                    IconButton(onClick = viewModel::onSortClicked) {
                        Icon(painter = painterResource(id = R.drawable.ic_sort), contentDescription = "Sort")
                    }

                    val context = LocalContext.current
                    CustomButton(
                        onClick = {
                            Toast.makeText(context, "Clicked Custom!!", Toast.LENGTH_SHORT).show()
                        }
                    ) {
                        Text("Our Custom Button")
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            AndroidVersionsList(viewItems = listState, onClick)
        }
    }
}

@Composable
private fun AndroidVersionsList(
    viewItems: ImmutableList<AndroidVersionsListViewModel.State.AndroidVersionViewItem>,
    onClick: (AndroidVersionInfo) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.testTag("Versions List")
    ) {
        itemsIndexed(
            items = viewItems,
            key = { index, viewItem -> "$index ${viewItem.info.apiVersion}" }
        ) { index, viewItem ->
            AndroidVersionInfoCard(viewItem, onClick)
        }
    }
}

@Composable
private fun AndroidVersionInfoCard(
    viewItem: AndroidVersionsListViewModel.State.AndroidVersionViewItem,
    onClick: (AndroidVersionInfo) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 120.dp)
            .clickable { onClick(viewItem.info) }
    ) {
        var isDetailExpanded by rememberSaveable(viewItem.info.apiVersion) { mutableStateOf(false) }

        Column(modifier = Modifier.padding(20.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier.size(64.dp),
                    painter = painterResource(id = R.drawable.ic_android),
                    contentDescription = "Android icon"
                )
                Column(modifier = Modifier.padding(start = 20.dp)) {

                    Row {
                        Text(
                            modifier = Modifier.weight(1f),
                            text = viewItem.title, style = MaterialTheme.typography.headlineMedium
                        )
                        IconButton(onClick = { isDetailExpanded = !isDetailExpanded }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_info),
                                contentDescription = "Info"
                            )
                        }
                    }
                    Text(viewItem.subtitle)
                }
            }

            AnimatedVisibility(visible = isDetailExpanded) {
                Text(
                    modifier = Modifier.padding(top = 4.dp),
                    text = viewItem.description.ifBlank { "No details available" },
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview(group = "Phone", fontScale = 1f, uiMode = UI_MODE_NIGHT_YES, device = Devices.NEXUS_5)
@Preview(group = "Phone", fontScale = 1.5f, uiMode = UI_MODE_NIGHT_YES, device = Devices.NEXUS_5)
@Preview(group = "Phone", fontScale = 1.25f, uiMode = UI_MODE_NIGHT_NO, device = Devices.NEXUS_5)
@Preview(group = "Phone", fontScale = 1f, uiMode = UI_MODE_NIGHT_YES, device = Devices.PIXEL_4)
@Preview(group = "Phone", fontScale = 1.5f, uiMode = UI_MODE_NIGHT_YES, device = Devices.PIXEL_4)
@Preview(group = "Phone", fontScale = 1.25f, uiMode = UI_MODE_NIGHT_NO, device = Devices.PIXEL_4)
@Preview(group = "Tablet", fontScale = 1f, uiMode = UI_MODE_NIGHT_YES, device = Devices.NEXUS_10)
@Preview(group = "Tablet", fontScale = 1.5f, uiMode = UI_MODE_NIGHT_YES, device = Devices.NEXUS_10)
@Preview(group = "Tablet", fontScale = 1.25f, uiMode = UI_MODE_NIGHT_NO, device = Devices.NEXUS_10)
@Preview(group = "Tablet", fontScale = 1f, uiMode = UI_MODE_NIGHT_YES, device = Devices.PIXEL_C)
@Preview(group = "Tablet", fontScale = 1.5f, uiMode = UI_MODE_NIGHT_YES, device = Devices.PIXEL_C)
@Preview(group = "Tablet", fontScale = 1.25f, uiMode = UI_MODE_NIGHT_NO, device = Devices.PIXEL_C)
annotation class StandardPreviews

@StandardPreviews
@Composable
internal fun PreviewAndroidVersionsListScreen() {
    AdvancedAndroidDemoTheme() {
        AndroidVersionsListScreen(onClick = {})
    }
}

@Preview
@Composable
internal fun PreviewAndroidVersionsList(
    @PreviewParameter(AndroidVersionsListPreviewParameterProvider::class)
    items: ImmutableList<AndroidVersionsListViewModel.State.AndroidVersionViewItem>
) {
    AdvancedAndroidDemoTheme() {
        AndroidVersionsList(viewItems = items, onClick = {})
    }
}

private class AndroidVersionsListPreviewParameterProvider
    : PreviewParameterProvider<List<AndroidVersionsListViewModel.State.AndroidVersionViewItem>> {
    override val values: Sequence<ImmutableList<AndroidVersionsListViewModel.State.AndroidVersionViewItem>>
        get() = sequenceOf(
            immutableListOf(),
            AndroidVersionsRepository.data.map { info ->
                AndroidVersionsListViewModel.State.AndroidVersionViewItem(
                    title = info.publicName,
                    subtitle = "${info.codename} - API ${info.apiVersion}",
                    description = info.details,
                    info = info
                )
            }.toImmutableList(),
            AndroidVersionsRepository.data.map { info ->
                AndroidVersionsListViewModel.State.AndroidVersionViewItem(
                    title = info.publicName,
                    subtitle = "${info.codename} - API ${info.apiVersion}",
                    description = info.details,
                    info = info
                )
            }.take(2).toImmutableList()
        )
}