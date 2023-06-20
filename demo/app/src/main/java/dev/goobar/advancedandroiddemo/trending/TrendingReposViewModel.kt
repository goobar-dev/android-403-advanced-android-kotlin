package dev.goobar.advancedandroiddemo.trending

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.goobar.advancedandroiddemo.data.RepoEntity
import dev.goobar.advancedandroiddemo.db.RepoDao
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class TrendingReposViewModel @Inject constructor(
    private val repoDao: RepoDao
) : ViewModel() {

    val repos: StateFlow<List<FeaturedRepoViewItem>> = repoDao
        .getAll()
        .map { it.map { it.toViewItem() } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    data class FeaturedRepoViewItem(
        val name: String,
        val displayName: String,
        val description: String,
        val createdBy: String,
    )
    fun RepoEntity.toViewItem() = FeaturedRepoViewItem(name, displayName, description, createdBy)
}