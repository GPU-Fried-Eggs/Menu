package com.loli.menu.models.mealdb

import com.google.gson.annotations.SerializedName

data class Meal(
    @SerializedName("idMeal") val id: String,
    @SerializedName("strMeal") val name: String,
    @SerializedName("strMealThumb") val thumbnailUrl: String,
    @SerializedName("strInstructions") val instructions: String = ""
)