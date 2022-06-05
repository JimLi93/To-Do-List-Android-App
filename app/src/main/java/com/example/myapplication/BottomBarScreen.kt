package com.example.myapplication

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.Pets
import androidx.compose.material.icons.outlined.Task
import androidx.compose.material.icons.outlined.TaskAlt
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route:String,
    val title: String,
    val icon: ImageVector
) {
    object Pet : BottomBarScreen(
        route = "pet",
        title = "Pet",
        icon = Icons.Outlined.Pets
    )
    object TaskList : BottomBarScreen(
        route = "taskList",
        title = "Tasks",
        icon = Icons.Outlined.Task
    )
    object StoryMap : BottomBarScreen(
        route = "storyMap",
        title = "Story",
        icon = Icons.Outlined.Book
    )
}
