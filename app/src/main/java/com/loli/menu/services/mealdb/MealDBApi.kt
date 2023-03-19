package com.loli.menu.services.mealdb

import com.loli.menu.models.mealdb.Categories
import com.loli.menu.models.mealdb.Meals
import com.loli.menu.services.MenuApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val MEAL_DB_URL = "https://www.themealdb.com/api/json/v1/1/"

interface MealDBApi: MenuApi {
    @GET("categories.php")
    override suspend fun getCategories(): Categories

    @GET("filter.php")
    override suspend fun getMealsByCategory(@Query("c") categoryName: String): Meals

    @GET("lookup.php")
    override suspend fun getCookBookByMeal(@Query("i") mealId: String): Meals

    companion object {
        private var instance: MealDBApi? = null

        fun getInstance(): MealDBApi {
            return (instance ?: createAdapter()?.build()?.create(MealDBApi::class.java))!!
        }

        private fun createAdapter(): Retrofit.Builder? {
            return Retrofit.Builder()
                .baseUrl(MEAL_DB_URL)
                .addConverterFactory(
                    GsonConverterFactory.create()
                )
        }
    }
}