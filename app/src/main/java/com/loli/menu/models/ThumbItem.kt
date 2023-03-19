package com.loli.menu.models

data class ThumbItem(
    val id: String,
    val name: String,
    val thumbnailUrl: String,
    val description: String = ""
) {
    companion object {
        val Default = ThumbItem("0", "", "")
    }
}