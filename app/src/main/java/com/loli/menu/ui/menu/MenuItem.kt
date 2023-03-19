package com.loli.menu.ui.menu

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.loli.menu.models.ThumbItem

@Composable
fun MenuItem(
    item: ThumbItem,
    onItemClicked: (id: String) -> Unit = {},
    expandable: Boolean = false,
    imageShape: Shape = RoundedCornerShape(6.dp)
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 8.dp)
            .clickable { onItemClicked(item.id) },
        shape = RoundedCornerShape(6.dp),
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 2.dp
    ) {
        var expanded by rememberSaveable { mutableStateOf(false) }

        Row(
            modifier = Modifier.animateContentSize()
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(item.thumbnailUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = "Food",
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .size(88.dp)
                    .padding(16.dp)
                    .clip(imageShape),
            )
            MenuItemDetail(
                item = item,
                lines = if (expanded) 8 else 2,
                modifier = Modifier
                    .padding(8.dp, 24.dp)
                    .fillMaxWidth(0.8f)
                    .align(Alignment.CenterVertically)
            )

            if (expandable) {
                Icon(
                    imageVector = Icons.Filled.run { if (expanded) KeyboardArrowUp else KeyboardArrowDown },
                    contentDescription = "Expand row icon",
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.run { if (expanded) Bottom else CenterVertically })
                        .noRippleClickable { expanded = !expanded }
                )
            }
        }
    }
}

inline fun Modifier.noRippleClickable(crossinline onClick: () -> Unit): Modifier = composed {
    clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() }
    ) {
        onClick()
    }
}

@Preview
@Composable
private fun MenuItemPreview() {
    Column {
        MenuItem(
            ThumbItem(
                "previewId",
                "previewName",
                "https://www.themealdb.com/images/category/beef.png",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. In eleifend eros nulla, ac blandit urna euismod rutrum. Suspendisse felis sem, tempor eget metus in, tempus elementum turpis. Mauris molestie ac orci vitae malesuada. Fusce dapibus condimentum diam, non rutrum leo pretium at. Morbi pellentesque sem lacus, at pellentesque tortor viverra at. Cras a gravida purus. Fusce tristique, eros mattis molestie egestas, turpis mi porttitor mauris, quis pellentesque velit orci quis velit. Suspendisse scelerisque venenatis vestibulum."
            )
        )
        MenuItem(
            ThumbItem(
                "previewId",
                "previewName",
                "https://www.themealdb.com/images/category/beef.png",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. In eleifend eros nulla, ac blandit urna euismod rutrum. Suspendisse felis sem, tempor eget metus in, tempus elementum turpis. Mauris molestie ac orci vitae malesuada. Fusce dapibus condimentum diam, non rutrum leo pretium at. Morbi pellentesque sem lacus, at pellentesque tortor viverra at. Cras a gravida purus. Fusce tristique, eros mattis molestie egestas, turpis mi porttitor mauris, quis pellentesque velit orci quis velit. Suspendisse scelerisque venenatis vestibulum."
            ),
            expandable = true
        )
    }
}