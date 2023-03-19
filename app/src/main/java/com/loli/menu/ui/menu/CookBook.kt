package com.loli.menu.ui.menu

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
import com.loli.menu.viewmodels.MenuUIState
import com.loli.menu.viewmodels.MenuViewModel

@Composable
fun CookBook(
    updateTopBar: (Pair<String, Boolean>) -> Unit = {},
    menuViewModel: MenuViewModel
) {
    when(menuViewModel.menuUIState) {
        is MenuUIState.Loading -> {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        }
        is MenuUIState.Success -> {
            val detail = (menuViewModel.menuUIState as MenuUIState.Success).detail
            updateTopBar(Pair(detail.name, true))
            MenuDetail(detail)
        }
        is MenuUIState.Error -> {
            Text(stringResource(R.string.loading_error))
        }
    }

}

@Preview
@Composable
private fun CookBookPreview() {
    CookBook(menuViewModel = viewModel())
}