package com.loli.menu.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loli.menu.models.DetailPage
import com.loli.menu.services.MenuService
import kotlinx.coroutines.launch

sealed interface MenuUIState {
    data class Success(val detail: DetailPage): MenuUIState

    object Error: MenuUIState

    object Loading: MenuUIState
}

class MenuViewModel(mealId: String) : ViewModel() {
    var menuUIState: MenuUIState by mutableStateOf(MenuUIState.Loading)
        private set

    init {
        getCookBook(mealId)
    }

    private fun getCookBook(id: String) {
        viewModelScope.launch {
            while (menuUIState === MenuUIState.Loading || menuUIState === MenuUIState.Error) {
                val menuService: MenuService?
                try {
                    menuService = MenuService.getInstance()
                    menuUIState = MenuUIState.Success(menuService.getCookBookByMeal(id))
                } catch (e: Exception) {
                    Log.d("MenuViewModel", "${e.message}")
                    menuUIState = MenuUIState.Error
                }
            }
        }
    }
}