package com.shubhanshi.animeexplorer.ui.screen.list

import android.R
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.shubhanshi.animeexplorer.domain.model.Anime
import com.shubhanshi.animeexplorer.util.UiState
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimeListScreen(
    state: StateFlow<UiState<List<Anime>>>,
    onClick: (Int) -> Unit
) {
    val uiState by state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Anime Explorer")
                }
            )
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {

            when (uiState) {

                is UiState.Loading -> {
                    CircularProgressIndicator()
                }

                is UiState.Error -> {
                    Text((uiState as UiState.Error).message)
                }

                is UiState.Success -> {
                    LazyColumn {
                        items((uiState as UiState.Success).data) { anime ->
                            AnimeListItem(anime, onClick)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AnimeListItem(
    anime: Anime,
    onClick: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onClick(anime.id) }
    ) {
        Row(modifier = Modifier.padding(8.dp)) {

            AsyncImage(
                model = anime.imageURL,
                contentDescription = null,
                modifier = Modifier.size(100.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(
                    text = anime.title,
                    style = MaterialTheme.typography.titleMedium
                )
                Text("Episodes: ${anime.episodes}")
                Text("Rating: ${anime.rating ?: "--"}")
            }
        }
    }
}