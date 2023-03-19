package com.loli.menu.ui.meals

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.loli.menu.models.ThumbItem
import com.loli.menu.ui.menu.MenuItem

@Composable
fun MealsList(
    thumbItems: List<ThumbItem>,
    onItemClicked: (id: String) -> Unit = {}
) {
    val scrollState = rememberLazyListState()

    LazyColumn(state = scrollState) {
        items(thumbItems) {
            MenuItem(
                item = it,
                onItemClicked = onItemClicked,
                expandable = false,
                imageShape = CircleShape
            )
        }
    }
}

@Preview
@Composable
private fun MealsListPreview() {
    MealsList(
        listOf(
            ThumbItem(
                "previewId",
                "previewName",
                "https://www.themealdb.com/images/category/beef.png"
            )
        )
    )
}