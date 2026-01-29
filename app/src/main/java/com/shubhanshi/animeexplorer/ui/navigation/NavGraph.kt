package com.shubhanshi.animeexplorer.ui.navigation

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.shubhanshi.animeexplorer.ui.screen.detail.AnimeDetailScreen
import com.shubhanshi.animeexplorer.ui.screen.list.AnimeListScreen
import com.shubhanshi.animeexplorer.util.RepositoryProvider
import com.shubhanshi.animeexplorer.viewmodel.*

@Composable
fun NavGraph() {

    val navController = rememberNavController()
    val context = LocalContext.current

    // 🔹 Single repository instance
    val repository = remember {
        RepositoryProvider.provideAnimeRepository(context)
    }

    // 🔹 LIST VIEWMODEL
    val listViewModel: AnimeListViewModel = viewModel(
        factory = AnimeListViewModelFactory(repository)
    )

    // 🔹 Load list once
    LaunchedEffect(Unit) {
        listViewModel.loadAnime(isOnline = true)
    }

    NavHost(
        navController = navController,
        startDestination = "list"
    ) {

        // =======================
        // 🔹 LIST SCREEN
        // =======================
        composable("list") {
            AnimeListScreen(
                state = listViewModel.state,
                onClick = { animeId ->
                    navController.navigate("detail/$animeId")
                }
            )
        }

        // =======================
        // 🔹 DETAIL SCREEN
        // =======================
        composable(
            route = "detail/{id}",
            arguments = listOf(
                navArgument("id") { type = NavType.IntType }
            )
        ) { backStackEntry ->

            val animeId =
                backStackEntry.arguments?.getInt("id") ?: return@composable

            // 🔹 DETAIL VIEWMODEL
            val detailViewModel: AnimeDetailViewModel = viewModel(
                factory = AnimeDetailViewModelFactory(repository)
            )

            // 🔹 Load detail when screen opens
            LaunchedEffect(animeId) {
                detailViewModel.loadAnimeDetails(animeId)
            }

            AnimeDetailScreen(
                state = detailViewModel.state,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}
