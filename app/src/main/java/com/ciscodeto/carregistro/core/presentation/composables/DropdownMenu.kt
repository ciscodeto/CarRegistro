package com.ciscodeto.carregistro.core.presentation.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun DropdownOptions(
    modifier: Modifier = Modifier,
    items: List<DropdownMenuInfo>,
) {
    var expanded by remember { mutableStateOf(false) }

    IconButton(onClick = { expanded = !expanded }) {
        Icon(Icons.Default.MoreVert, contentDescription = "More options")
    }

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false },
        modifier = modifier,
    ) {
        for (item in items) {
            DropdownMenuItem(
                text = { Text(item.text) },
                leadingIcon = item.leadingIcon,
                trailingIcon = item.trailingIcon,
                onClick = { item.onClick()
                    expanded = false }
            )
        }
    }
}