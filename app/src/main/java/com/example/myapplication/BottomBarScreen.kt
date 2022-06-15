package com.example.myapplication

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
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
        icon = Icons.Outlined.Event
    )
    object StoryMap : BottomBarScreen(
        route = "storyMap",
        title = "Story",
        icon = Icons.Outlined.AutoStories
        //icon = Icons.Outlined.ImportContacts
    )
}
