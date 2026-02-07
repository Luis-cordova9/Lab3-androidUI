package com.curso.android.module2.stream.ui.screens
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.curso.android.module2.stream.ui.viewmodel.HomeViewModel
import com.curso.android.module2.stream.ui.viewmodel.HomeUiState

@Composable
fun HighlightsScreen(
    viewModel: HomeViewModel,
    onSongClick: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = "Mis Favoritos",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        when (val state = uiState) {
            is HomeUiState.Success -> {
                /**
                 * EL FILTRO (Aquí suele estar el error):
                 * Como las canciones están dentro de categorías,
                 * primero "aplanamos" todas las listas de canciones en una sola
                 * y luego filtramos las que tengan isFavorite == true.
                 */
                val favorites = state.categories
                    .flatMap { it.songs }
                    .filter { it.isFavorite }

                if (favorites.isEmpty()) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("No has marcado ninguna canción como favorita")
                    }
                } else {
                    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        items(favorites) { song ->
                            SongCard(
                                song = song,
                                onClick = { onSongClick(song.id) },
                                onFavClick = { id -> viewModel.toggleFavorite(id) }
                            )
                        }
                    }
                }
            }
            is HomeUiState.Loading -> CircularProgressIndicator()
            else -> Text("Error al cargar")
        }
    }
}