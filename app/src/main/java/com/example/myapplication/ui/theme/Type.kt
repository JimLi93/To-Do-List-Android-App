package com.example.myapplication.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import androidx.compose.ui.text.font.Font

val fontFamily1 = FontFamily(
    Font(R.font.oregano)
)
// Set of Material typography styles to start with
val Typography = Typography(
    h1 = TextStyle(
        fontFamily = fontFamily1,
        fontWeight = FontWeight.Normal,
        fontSize = 70.sp
    ),
    h2 = TextStyle(
        fontFamily = fontFamily1,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 60.sp
    ),
    h3 = TextStyle(
        fontFamily = fontFamily1,
        fontWeight = FontWeight.Normal,
        fontSize = 50.sp
    ),
    h4 = TextStyle(
        fontFamily = fontFamily1,
        fontWeight = FontWeight.Normal,
        fontSize = 45.sp
    ),
    h5 = TextStyle(
        fontFamily = fontFamily1,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp
    ),
    h6 = TextStyle(
        fontFamily = fontFamily1,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp
    ),
    body1 = TextStyle(
        fontFamily = fontFamily1,
        fontWeight = FontWeight.Normal,
        fontSize = 30.sp
    ),
    body2 = TextStyle(
        fontFamily = fontFamily1,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp
    ),
    button = TextStyle(
        fontFamily = fontFamily1,
        fontWeight = FontWeight.SemiBold,
        fontSize = 30.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = fontFamily1,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = fontFamily1,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)