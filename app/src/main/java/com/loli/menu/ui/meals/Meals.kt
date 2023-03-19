package com.loli.menu.ui.meals

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.loli.menu.R
import com.loli.menu.viewmodels.MainViewModel
import com.loli.menu.viewmodels.MealUIState
import com.loli.menu.viewmodels.MealsViewModel

@Composable
fun Meals(
    updateTopBar: (Pair<String, Boolean>) -> Unit = {},
    onNavigationRequested: (String) -> Unit = {},
    mealsViewModel: MealsViewModel
) {
    updateTopBar(Pair(mealsViewModel.category.name, true))

    when(mealsViewModel.mealUIState) {
        is MealUIState.Loading -> {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        }
        is MealUIState.Success -> {
            MealsList((mealsViewModel.mealUIState as MealUIState.Success).meals) {
                onNavigationRequested(it)
            }
        }
        is MealUIState.Error -> {
            Text(stringResource(R.string.loading_error))
        }
    }
}

@Preview
@Composable
private fun MealsPreview() {
    Meals(mealsViewModel = viewModel())
}