package com.example.myapplication.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun FAB(
    imageVector: ImageVector,
    backgroundColor: Color,
    size: Int,
    padding: Int,
    onClickAction: () -> Unit,
    string: String
){
    FloatingActionButton(
        onClick = onClickAction, modifier = Modifier
            .padding(padding.dp)
            .size(size.dp),
        backgroundColor = backgroundColor, shape = CircleShape
    ) {
        Icon(imageVector, string, Modifier.size(40.dp))
    }
}