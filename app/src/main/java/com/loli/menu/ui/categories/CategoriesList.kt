package com.loli.menu.ui.categories

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.loli.menu.models.ThumbItem
import com.loli.menu.ui.menu.MenuItem

@Composable
fun CategoriesList(
    thumbItems: List<ThumbItem>,
    onItemClicked: (id: String) -> Unit = {}
) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        items(thumbItems) {
            MenuItem(
                item = it,
                onItemClicked = onItemClicked,
                expandable = true
            )
        }
    }
}

@Preview
@Composable
private fun CategoriesListPreview() {
    CategoriesList(
        listOf(
            ThumbItem(
                "previewId",
                "previewName",
                "https://www.themealdb.com/images/category/beef.png",
                "previewDescription"
            )
        )
    )
}