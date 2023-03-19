package com.loli.menu.models

data class DetailPage(
    val id: String,
    val name: String,
    val thumbnailUrl: String,
    val instructions: String
) {
    companion object {
        val Default = DetailPage("0", "", "", "")
    }
}