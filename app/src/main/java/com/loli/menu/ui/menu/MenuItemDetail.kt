package com.loli.menu.ui.menu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.loli.menu.models.ThumbItem

@Composable
fun MenuItemDetail(item: ThumbItem?, lines: Int, modifier: Modifier) {
    Column(modifier = modifier) {
        Text(
            text = item?.name ?: "",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.subtitle1,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        if (item?.description?.trim()?.isNotEmpty() == true)
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    text = item.description.trim(),
                    textAlign = TextAlign.Start,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.caption,
                    maxLines = lines
                )
            }
    }
}

@Preview
@Composable
private fun MenuItemDetailPreview() {
    MenuItemDetail(
        item = ThumbItem(
            "previewId",
            "previewName",
            "https://www.themealdb.com/images/category/beef.png",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. In eleifend eros nulla, ac blandit urna euismod rutrum. Suspendisse felis sem, tempor eget metus in, tempus elementum turpis. Mauris molestie ac orci vitae malesuada. Fusce dapibus condimentum diam, non rutrum leo pretium at. Morbi pellentesque sem lacus, at pellentesque tortor viverra at. Cras a gravida purus. Fusce tristique, eros mattis molestie egestas, turpis mi porttitor mauris, quis pellentesque velit orci quis velit. Suspendisse scelerisque venenatis vestibulum."
        ),
        lines = 3,
        modifier = Modifier.padding(16.dp)
    )
}