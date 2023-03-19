package com.loli.menu.ui.home

import androidx.compose.runtime.Composable

@Composable
fun Home(
    updateTopBar: (Pair<String, Boolean>) -> Unit = {},
) {
    updateTopBar(Pair("Home", false))


}