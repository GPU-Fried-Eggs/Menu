package com.loli.menu.ui.navigation

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*

@Composable
fun TopBarAction() {
    var expanded: Boolean by remember { mutableStateOf(false) }

    IconButton(
        onClick = { expanded = !expanded }
    ) {
        Icon(Icons.Filled.MoreVert, "More")
    }
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        DropdownMenuItem(onClick = {}) {
            Text("Settings")
        }
    }
}