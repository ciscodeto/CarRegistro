package com.ciscodeto.carregistro.cars.presentation.screens.composables.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ciscodeto.carregistro.cars.presentation.model.CarUi

@Composable
fun CarListElement(
    carUi: CarUi,
    modifier: Modifier = Modifier,
    onEditClick: () -> Unit = {},
    onDeleteClick: () -> Unit = {},
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = carUi.manufacturer.uppercase(),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        lineHeight = 12.sp
                    )
                    Text(
                        text = carUi.model.uppercase(),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Black,
                        lineHeight = 24.sp
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    ConfigCard(
                        icon = {
                            Icon(
                                Icons.Filled.DateRange,
                                contentDescription = "Ano",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        },
                        text = "ANO ${carUi.year}"
                    )
                    ConfigCard(
                        icon = {
                            Icon(
                                Icons.Filled.Settings,
                                contentDescription = "Motor",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        },
                        text = "MOTOR ${carUi.motorization}"
                    )
                }

                Spacer(modifier = Modifier.width(4.dp))

                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    ActionButton(onClick = onEditClick, icon = {
                        Icon(
                            Icons.Default.Edit,
                            contentDescription = "Editar",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    })
                    ActionButton(onClick = onDeleteClick, icon = {
                        Icon(
                            Icons.Default.Delete,
                            contentDescription = "Excluir",
                            tint = MaterialTheme.colorScheme.error
                        )
                    })
                }
            }
        }
    }
}