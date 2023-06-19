package dev.goobar.advancedandroiddemo.versions

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dev.goobar.advancedandroiddemo.data.AndroidVersionInfo
import dev.goobar.advancedandroiddemo.data.AndroidVersionsRepository

class AndroidVersionsListViewModel : ViewModel() {

    val versionsListState: MutableState<List<AndroidVersionInfo>> =
        mutableStateOf(AndroidVersionsRepository.data)
}