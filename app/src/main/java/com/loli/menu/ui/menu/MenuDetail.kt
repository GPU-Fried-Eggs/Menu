package com.loli.menu.ui.menu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.loli.menu.models.DetailPage

@Composable
fun MenuDetail(detail: DetailPage) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = detail.name,
            fontSize = 32.sp,
            fontStyle = FontStyle.Italic
        )
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(detail.thumbnailUrl)
                .crossfade(true)
                .build(),
            contentDescription = "Food",
            modifier = Modifier
                .size(256.dp)
                .padding(16.dp)
        )
        Text(
            text = detail.instructions,
            textAlign = TextAlign.Start
        )
    }
}

@Preview
@Composable
private fun MenuDetailPreview() {
    MenuDetail(
        detail = DetailPage(
            "previewId",
            "previewName",
            "https://www.themealdb.com/images/category/beef.png",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. In eleifend eros nulla, ac blandit urna euismod rutrum. Suspendisse felis sem, tempor eget metus in, tempus elementum turpis. Mauris molestie ac orci vitae malesuada. Fusce dapibus condimentum diam, non rutrum leo pretium at. Morbi pellentesque sem lacus, at pellentesque tortor viverra at. Cras a gravida purus. Fusce tristique, eros mattis molestie egestas, turpis mi porttitor mauris, quis pellentesque velit orci quis velit. Suspendisse scelerisque venenatis vestibulum."
        )
    )
}