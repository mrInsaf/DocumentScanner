package com.mrinsaf.documentscanner.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.mrinsaf.documentscanner.R


val rosatomFontFamily = FontFamily(
    Font(R.font.rosatom_regular, FontWeight.Normal),
    Font(R.font.rosatom_bold, FontWeight.Bold),
    Font(R.font.rosatom_light, FontWeight.Light),
    Font(R.font.rosatom_italic, FontWeight.Normal, FontStyle.Italic),
)

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = rosatomFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = rosatomFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
    ),
    bodySmall = TextStyle(
        fontFamily = rosatomFontFamily,
        fontWeight = FontWeight.Light,
        fontSize = 12.sp,
    ),

    headlineLarge = TextStyle(
        fontFamily = rosatomFontFamily,
        fontWeight = FontWeight.Bold,
        color = BlueMain,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.sp
    ),

    headlineMedium = TextStyle(
        fontFamily = rosatomFontFamily,
        fontWeight = FontWeight.Bold,
        color = BlueMain,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.sp
    ),

    headlineSmall = TextStyle(
        fontFamily = rosatomFontFamily,
        fontWeight = FontWeight.Bold,
        color = BlueMain,
        fontSize = 22.sp,
//        lineHeight = 32.sp,
//        letterSpacing = 0.sp
    ),
)
