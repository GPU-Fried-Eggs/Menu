package com.loli.menu.ui.categories

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
import com.loli.menu.viewmodels.CategoriesViewModel
import com.loli.menu.viewmodels.CategoryUIState

@Composable
fun Categories(
    updateTopBar: (Pair<String, Boolean>) -> Unit = {},
    onNavigationRequested: (String) -> Unit = {},
    categoriesViewModel: CategoriesViewModel = viewModel()
) {
    updateTopBar(Pair(stringResource(R.string.nav_categories), false))

    when(categoriesViewModel.categoryUIState) {
        is CategoryUIState.Loading -> {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        }
        is CategoryUIState.Success -> {
            CategoriesList((categoriesViewModel.categoryUIState as CategoryUIState.Success).categories) {
                onNavigationRequested(it)
            }
        }
        is CategoryUIState.Error -> {
            Text(stringResource(R.string.loading_error))
        }
    }
}

@Preview
@Composable
private fun CategoriesPreview() {
    Categories()
}