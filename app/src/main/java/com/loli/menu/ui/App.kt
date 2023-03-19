package com.loli.menu.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.loli.menu.ui.categories.Categories
import com.loli.menu.ui.home.Home
import com.loli.menu.ui.meals.Meals
import com.loli.menu.ui.menu.CookBook
import com.loli.menu.ui.menu.MenuDetail
import com.loli.menu.ui.navigation.TopBar
import com.loli.menu.viewmodels.MainViewModel
import com.loli.menu.viewmodels.MealsViewModel
import com.loli.menu.viewmodels.MenuViewModel

sealed class NavigationConfig(var route: String) {
    object Home : NavigationConfig("home")
    object Categories: NavigationConfig("categories")
    object Category: NavigationConfig("category/{categoryId}")
    object Menu: NavigationConfig("menu/{menuId}")
}

@Composable
fun App(mainViewModel: MainViewModel = viewModel()) {
    val scaffoldState = rememberScaffoldState()
    val navController = rememberNavController()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopBar(navController, mainViewModel.screenTitle, mainViewModel.popEnable) }
    ) {
        NavHost(
            navController = navController,
            startDestination = NavigationConfig.Categories.route,
            modifier = Modifier.padding(it)
        ) {
            composable(NavigationConfig.Home.route) {
                Home(
                    updateTopBar = { pair -> mainViewModel.updateTopBar(pair) },
                )
            }
            composable(NavigationConfig.Categories.route) {
                Categories(
                    updateTopBar = { pair -> mainViewModel.updateTopBar(pair) },
                    onNavigationRequested = { id -> navController.navigate("category/$id") }
                )
            }
            composable(
                NavigationConfig.Category.route,
                listOf(navArgument("categoryId") {
                    type = NavType.StringType
                })
            ) { entry ->
                Meals(
                    updateTopBar = { pair -> mainViewModel.updateTopBar(pair) },
                    onNavigationRequested = { id -> navController.navigate("menu/$id") },
                    mealsViewModel = viewModel(factory = viewModelFactory {
                        MealsViewModel(
                            entry.arguments?.getString("categoryId")
                                ?: throw IllegalStateException("No categoryId was passed to destination.")
                        )
                    }
                ))
            }
            composable(
                NavigationConfig.Menu.route,
                listOf(navArgument("menuId") {
                    type = NavType.StringType
                })
            ) { entry ->
                CookBook(
                    updateTopBar = { pair -> mainViewModel.updateTopBar(pair) },
                    menuViewModel = viewModel(factory = viewModelFactory {
                        MenuViewModel(
                            entry.arguments?.getString("menuId")
                                ?: throw IllegalStateException("No menuId was passed to destination.")
                        )
                    })
                )
            }
        }
    }
}

inline fun <VM : ViewModel> viewModelFactory(crossinline f: () -> VM) =
    object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>):T = f() as T
    }

@Preview
@Composable
private fun AppPreview() {
    App()
}