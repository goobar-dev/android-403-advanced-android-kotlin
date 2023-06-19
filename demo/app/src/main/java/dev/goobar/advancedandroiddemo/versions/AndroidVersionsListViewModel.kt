package dev.goobar.advancedandroiddemo.versions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.goobar.advancedandroiddemo.data.AndroidVersionInfo
import dev.goobar.advancedandroiddemo.data.AndroidVersionsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.plus

enum class Sort {
    ASCENDING, DESCENDING
}

class AndroidVersionsListViewModel : ViewModel() {

    private val _sort: MutableStateFlow<Sort> = MutableStateFlow(Sort.ASCENDING)
    private val _versions: MutableStateFlow<List<AndroidVersionInfo>> = MutableStateFlow(AndroidVersionsRepository.data)

//    val state = combine(_sort, _versions) { sort, versions ->
//        val sortedItems = when (sort) {
//            Sort.ASCENDING -> versions.sortedBy { it.apiVersion }
//            Sort.DESCENDING -> versions.sortedByDescending { it.apiVersion }
//        }.map { info ->
//            State.AndroidVersionViewItem(
//                title = info.publicName,
//                subtitle = "${info.codename} - API ${info.apiVersion}",
//                description = info.details,
//                info = info
//            )
//        }
//        State(sortedItems)
//    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(3_000), State(emptyList()))

    val state = flow {
        val items = AndroidVersionsRepository.data
        val itemsToReturn = mutableListOf<AndroidVersionInfo>()

        items.forEach { info ->
            delay(4000)
            itemsToReturn.add(info)
            emit(
                State(
                    itemsToReturn.map { info ->
                        State.AndroidVersionViewItem(
                            title = info.publicName,
                            subtitle = "${info.codename} - API ${info.apiVersion}",
                            description = info.details,
                            info = info
                        )
                    }
                )
            )
        }
    }.stateIn(viewModelScope + Dispatchers.Main.immediate, SharingStarted.WhileSubscribed(3_000), State(emptyList()))

    fun onSortClicked() {
        _sort.update { currentSort ->
            when (currentSort) {
                Sort.ASCENDING -> Sort.DESCENDING
                Sort.DESCENDING -> Sort.ASCENDING
            }
        }
    }

    data class State(val versionsList: List<AndroidVersionViewItem>) {
        data class AndroidVersionViewItem(
            val title: String,
            val subtitle: String,
            val description: String,
            val info: AndroidVersionInfo
        )
    }
}