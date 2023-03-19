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

sealed interface MealUIState {
    data class Success(val meals: List<ThumbItem>): MealUIState

    object Error: MealUIState

    object Loading: MealUIState
}

class MealsViewModel(category: String) : ViewModel() {
    var mealUIState: MealUIState by mutableStateOf(MealUIState.Loading)
        private set

    var category: ThumbItem by mutableStateOf(ThumbItem.Default)
        private set

    init {
        getMealsList(category)
    }

    private fun getMealsList(id: String) {
        viewModelScope.launch {
            while (mealUIState === MealUIState.Loading || mealUIState === MealUIState.Error) {
                var menuService: MenuService?
                try {
                    menuService = MenuService.getInstance()
                    category = menuService.getMenuCategories().first { id == it.id }
                    mealUIState = MealUIState.Success(menuService.getMealsByCategory(id))
                } catch (e: Exception) {
                    Log.d("MealsViewModel", "${e.message}")
                    mealUIState = MealUIState.Error
                }
            }
        }
    }
}