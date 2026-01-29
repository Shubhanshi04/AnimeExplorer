package com.shubhanshi.animeexplorer.ui.screen.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.shubhanshi.animeexplorer.data.remote.dto.AnimeDetailsDto
import com.shubhanshi.animeexplorer.ui.components.VideoPlayer
import com.shubhanshi.animeexplorer.util.UiState
import com.shubhanshi.animeexplorer.util.openYouTube
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimeDetailScreen(
    state: StateFlow<UiState<AnimeDetailsDto>>,
    onBackClick: () -> Unit
) {
    val uiState by state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Anime Details") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
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
                    val anime = (uiState as UiState.Success).data

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                            .verticalScroll(rememberScrollState())
                    ) {
                        val context = LocalContext.current
                        val trailer = anime.trailer

                        val watchUrl = when {
                            trailer?.youtubeId != null ->
                                "https://www.youtube.com/watch?v=${trailer.youtubeId}"

                            trailer?.url != null ->
                                trailer.url

                            trailer?.embedUrl != null ->
                                trailer.embedUrl   // fallback

                            else -> null
                        }

                        if (watchUrl != null) {
                            Button(
                                onClick = { openYouTube(context, watchUrl) },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text("▶ Watch Trailer on YouTube")
                            }
                        } else {
                            Text("Trailer not available")
                        }


                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = anime.title,
                            style = MaterialTheme.typography.headlineSmall
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text("Rating: ${anime.score ?: "N/A"}")
                        Text("Episodes: ${anime.episodes ?: "N/A"}")

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = "Synopsis",
                            style = MaterialTheme.typography.titleMedium
                        )

                        Text(anime.synopsis ?: "No synopsis available")

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = "Genres: ${
                                anime.genres?.joinToString { it.name }
                            }"
                        )
                    }
                }
            }
        }
    }
}
