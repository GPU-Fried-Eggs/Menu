package com.loli.menu.services

import com.loli.menu.models.mealdb.Categories
import com.loli.menu.models.mealdb.Meals

interface MenuApi {
    suspend fun getCategories(): Categories

    suspend fun getMealsByCategory(categoryName: String): Meals

    suspend fun getCookBookByMeal(mealId: String): Meals
}