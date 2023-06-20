package dev.goobar.advancedandroidlab

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import dev.goobar.advancedandroidlab.domain.Planet
import dev.goobar.advancedandroidlab.domain.PlanetRepository
import dev.goobar.advancedandroidlab.planetslist.PlanetsListScreen
import dev.goobar.advancedandroidlab.planetslist.PlanetsListViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.junit.Rule
import org.junit.Test

class StarWarsPlanetListTest {

    private val sampleData = listOf(
        Planet("Hoth"),
        Planet("Lothal"),
        Planet("Mandalore"),
    )

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun planetListDisplayedOnHomeScreenLoad() {
        composeTestRule.setContent {
            PlanetsListScreen(
                viewModel = PlanetsListViewModel(
                    object : PlanetRepository {
                        override suspend fun getPlanets(): List<Planet> = withContext(Dispatchers.Main.immediate) {
                            sampleData
                        }
                    }
                ),
                onClick = {}
            )
        }

        composeTestRule
            .onNodeWithTag(testTag = "Planets List")
            .assertExists(errorMessageOnFail = "Planets List Missing")
    }
}