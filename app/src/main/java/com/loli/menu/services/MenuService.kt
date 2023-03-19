package com.loli.menu.services

import com.loli.menu.models.DetailPage
import com.loli.menu.models.ThumbItem
import com.loli.menu.models.mealdb.Categories
import com.loli.menu.models.mealdb.Meals
import com.loli.menu.services.mealdb.MealDBApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MenuService(private val api: MenuApi) {
    private var cached: List<ThumbItem>? = null

    suspend fun getMenuCategories(): List<ThumbItem> = withContext(Dispatchers.IO) {
        cached ?: api.getCategories().mapCategoriesToItems()
    }

    suspend fun getMealsByCategory(id: String): List<ThumbItem> = withContext(Dispatchers.IO) {
        val categoryName = getMenuCategories().first { id == it.id }.name
        api.getMealsByCategory(categoryName).mapMealsToItems()
    }

    suspend fun getCookBookByMeal(id: String): DetailPage = withContext(Dispatchers.IO) {
        api.getCookBookByMeal(id).mapDetailToPage()
    }

    private fun Categories.mapCategoriesToItems(): List<ThumbItem> {
        return this.categories.map {
            ThumbItem(it.id, it.name, it.thumbnailUrl, it.description)
        }
    }

    private fun Meals.mapMealsToItems(): List<ThumbItem> {
        return this.meals.map {
            ThumbItem(it.id, it.name, it.thumbnailUrl)
        }
    }

    private fun Meals.mapDetailToPage(): DetailPage {
        return this.meals.first().run {
            DetailPage(id, name, thumbnailUrl, instructions)
        }
    }

    companion object {
        private var instance: MenuService? = null

        fun getInstance(api: MenuApi = MealDBApi.getInstance()): MenuService {
            return instance ?: MenuService(api)
        }
    }
}