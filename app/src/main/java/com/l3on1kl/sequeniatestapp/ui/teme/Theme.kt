package com.l3on1kl.sequeniatestapp.ui.teme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.l3on1kl.sequeniatestapp.R

@Composable
fun SequeniaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> darkColorScheme(
            primary = Color(colorResource(R.color.colorPrimary).toArgb()),
            onPrimary = Color(colorResource(R.color.colorOnPrimary).toArgb()),
            primaryContainer = Color(colorResource(R.color.colorPrimaryContainer).toArgb()),
            onPrimaryContainer = Color(colorResource(R.color.colorOnPrimaryContainer).toArgb()),
            background = Color(colorResource(R.color.colorBackground).toArgb()),
            surface = Color(colorResource(R.color.colorSurface).toArgb()),
            onSurface = Color(colorResource(R.color.colorOnSurface).toArgb()),
        )

        else -> lightColorScheme(
            primary = Color(colorResource(R.color.colorPrimary).toArgb()),
            onPrimary = Color(colorResource(R.color.colorOnPrimary).toArgb()),
            primaryContainer = Color(colorResource(R.color.colorPrimaryContainer).toArgb()),
            onPrimaryContainer = Color(colorResource(R.color.colorOnPrimaryContainer).toArgb()),
            background = Color(colorResource(R.color.colorBackground).toArgb()),
            surface = Color(colorResource(R.color.colorSurface).toArgb()),
            onSurface = Color(colorResource(R.color.colorOnSurface).toArgb()),
        )
    }

    val appShapes = Shapes(
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(8.dp),
        large = RoundedCornerShape(16.dp)
    )

    val appTypography = Typography(
        bodyLarge = TextStyle(
            fontFamily = FontFamily.Default,
            fontSize = 16.sp
        ),
        titleLarge = TextStyle(
            fontFamily = FontFamily.Default,
            fontSize = 22.sp
        ),
        labelLarge = TextStyle(
            fontFamily = FontFamily.Default,
            fontSize = 14.sp
        )
    )

    MaterialTheme(
        colorScheme = colorScheme,
        typography = appTypography,
        shapes = appShapes,
        content = content
    )
}
