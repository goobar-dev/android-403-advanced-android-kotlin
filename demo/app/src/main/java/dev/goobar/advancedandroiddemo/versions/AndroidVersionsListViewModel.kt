package dev.goobar.advancedandroiddemo.versions

import androidx.lifecycle.ViewModel
import dev.goobar.advancedandroiddemo.data.AndroidVersionInfo
import dev.goobar.advancedandroiddemo.data.AndroidVersionsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AndroidVersionsListViewModel : ViewModel() {

    val state: StateFlow<AndroidVersionsListViewModel.State> =
        MutableStateFlow(
            State(AndroidVersionsRepository.data.map { info ->
                State.AndroidVersionViewItem(
                    title = info.publicName,
                    subtitle = "${info.codename} - API ${info.apiVersion}",
                    description = info.details,
                    info = info
                )
            })
        )

    data class State(val versionsList: List<AndroidVersionViewItem>) {
        data class AndroidVersionViewItem(
            val title: String,
            val subtitle: String,
            val description: String,
            val info: AndroidVersionInfo
        )
    }
}