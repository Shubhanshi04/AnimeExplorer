package com.shubhanshi.animeexplorer.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.shubhanshi.animeexplorer.domain.model.Anime
import com.shubhanshi.animeexplorer.ui.screen.details.AnimeDetailScreen
import com.shubhanshi.animeexplorer.ui.screen.list.AnimeListScreen
import com.shubhanshi.animeexplorer.util.RepositoryProvider
import com.shubhanshi.animeexplorer.util.UiState
import com.shubhanshi.animeexplorer.viewmodel.AnimeListViewModel
import com.shubhanshi.animeexplorer.viewmodel.AnimeListViewModelFactory

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val repository = remember {
        RepositoryProvider.provideAnimeRepository(context)
    }
    val viewModel: AnimeListViewModel = viewModel(
        factory = AnimeListViewModelFactory(repository)
    )

    LaunchedEffect(Unit) {
        viewModel.loadAnime(isOnline = true)
    }

    NavHost(navController = navController,
        startDestination = "list") {

        composable("list") {
            AnimeListScreen(
                state = viewModel.state,
                onClick = { animeId ->
                    navController.navigate("detail/$animeId")
                }
            )
        }
        composable(route = "detail/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) {backStackEntry ->
            val animeId = backStackEntry.arguments?.getInt("id") ?: 0
            AnimeDetailScreen(
                animeId= animeId,
                onBackClick = {
                    navController.popBackStack()
                }
            )

        }
    }
}