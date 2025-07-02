package com.ciscodeto.carregistro.core.presentation.composables

import androidx.compose.runtime.Composable

data class DropdownMenuInfo(
    val text: String,
    val leadingIcon: @Composable () -> Unit = {},
    val trailingIcon: @Composable () -> Unit = {},
    val onClick: () -> Unit
)
