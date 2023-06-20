package dev.goobar.advancedandroidlab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.goobar.advancedandroidlab.network.PlanetDTO
import dev.goobar.advancedandroidlab.network.SWAPINetworkClient
import dev.goobar.advancedandroidlab.ui.theme.AdvancedAndroidLabTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AdvancedAndroidLabTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var planets by remember { mutableStateOf<List<PlanetDTO>>(emptyList()) }
                    LaunchedEffect(this) {
                        launch(Dispatchers.IO) {
                            planets = SWAPINetworkClient.getPlanets().results
                        }
                    }

                    if (planets.isEmpty()) {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            CircularProgressIndicator(modifier = Modifier.size(56.dp))
                        }
                    } else {
                        LazyColumn() {
                            items(planets) { planet ->
                                Text(planet.name)
                            }
                        }
                    }
                }
            }
        }
    }
}