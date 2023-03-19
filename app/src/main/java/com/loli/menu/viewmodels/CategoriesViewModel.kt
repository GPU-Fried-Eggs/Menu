package com.loli.menu.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loli.menu.models.ThumbItem
import com.loli.menu.services.MenuService
import kotlinx.coroutines.launch

sealed interface CategoryUIState {
    data class Success(val categories: List<ThumbItem>): CategoryUIState

    object Error: CategoryUIState

    object Loading: CategoryUIState
}

class CategoriesViewModel : ViewModel() {
    var categoryUIState: CategoryUIState by mutableStateOf(CategoryUIState.Loading)
        private set

    init {
        getCategoriesList()
    }

    private fun getCategoriesList() {
        viewModelScope.launch {
            while (categoryUIState === CategoryUIState.Loading || categoryUIState === CategoryUIState.Error) {
                var menuService: MenuService?
                try {
                    menuService = MenuService.getInstance()
                    categoryUIState = CategoryUIState.Success(menuService.getMenuCategories())
                } catch (e: Exception) {
                    Log.d("CategoriesViewModel", "${e.message}")
                    categoryUIState = CategoryUIState.Error
                }
            }
        }
    }
}