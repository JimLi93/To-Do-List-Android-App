package com.example.myapplication.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color

private val lineleftdp: List<Float> = listOf(580f, 1020f, 1025f, 390f,
                                             395f, 625f , 877f ,1035f)
private val lineupdp: List<Float> = listOf(350f, 500f, 585f, 860f,
                                           1025f, 1135f , 1358f ,1705f)

@Composable
fun DrawMapLine(num: Int){
    Canvas(modifier = Modifier.fillMaxSize()) {

        drawLine(
            start = Offset(x = lineleftdp[num], y = lineupdp[num]),
            end = Offset(x = lineleftdp[num+1], y = lineupdp[num+1]),
            color = Color.Black,
            strokeWidth = 10F
        )

    }
}