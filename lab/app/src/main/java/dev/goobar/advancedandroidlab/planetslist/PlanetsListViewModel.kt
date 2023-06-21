package dev.goobar.advancedandroidlab.planetslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.goobar.advancedandroidlab.domain.NetworkPlanetRepository
import dev.goobar.advancedandroidlab.domain.Planet
import dev.goobar.advancedandroidlab.domain.PlanetRepository
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import javax.inject.Inject

@HiltViewModel
class PlanetsListViewModel @Inject constructor(private val repository: PlanetRepository) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.refresh()
        }
    }

    val state = repository.getPlanets()
        .map { State(it.toImmutableList()) }
        .stateIn(viewModelScope + Dispatchers.IO, SharingStarted.WhileSubscribed(5_000), State(persistentListOf()))

    data class State(
        val planets: ImmutableList<Planet>
    )

}