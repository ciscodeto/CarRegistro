package com.ciscodeto.carregistro.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.ciscodeto.carregistro.R

val oswaldFamily = FontFamily(
    Font(R.font.oswald_regular, FontWeight.Normal),
    Font(R.font.oswald_medium, FontWeight.Medium),
    Font(R.font.oswald_bold, FontWeight.Bold),
    Font(R.font.oswald_light, FontWeight.Light),
)

val Typography = Typography().run {
    copy(
        displayLarge = displayLarge.copy(fontFamily = oswaldFamily),
        displayMedium = displayMedium.copy(fontFamily = oswaldFamily),
        displaySmall = displaySmall.copy(fontFamily = oswaldFamily),
        headlineLarge = headlineLarge.copy(fontFamily = oswaldFamily),
        headlineMedium = headlineMedium.copy(fontFamily = oswaldFamily),
        headlineSmall = headlineSmall.copy(fontFamily = oswaldFamily),
        titleLarge = titleLarge.copy(fontFamily = oswaldFamily),
        titleMedium = titleMedium.copy(fontFamily = oswaldFamily),
        titleSmall = titleSmall.copy(fontFamily = oswaldFamily),
        bodyLarge = bodyLarge.copy(fontFamily = oswaldFamily),
        bodyMedium = bodyMedium.copy(fontFamily = oswaldFamily),
        bodySmall = bodySmall.copy(fontFamily = oswaldFamily),
        labelLarge = labelLarge.copy(fontFamily = oswaldFamily),
        labelMedium = labelMedium.copy(fontFamily = oswaldFamily),
        labelSmall = labelSmall.copy(fontFamily = oswaldFamily),
    )
}