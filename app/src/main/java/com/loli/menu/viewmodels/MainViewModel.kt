package com.loli.menu.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    var screenTitle by mutableStateOf("master")
        private set

    var popEnable by mutableStateOf(false)
        private set

    fun updateScreenTitle(value: String) {
        screenTitle = value
    }

    fun updatePopEnable(value: Boolean) {
        popEnable = value
    }

    fun updateTopBar(value: Pair<String, Boolean>) {
        updateScreenTitle(value.first)
        updatePopEnable(value.second)
    }
}