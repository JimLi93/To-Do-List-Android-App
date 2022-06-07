package com.example.myapplication

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = "greeting",
        modifier = modifier
    ) {
        composable("addtask") { AddTask(navController = navController) }
        composable("greeting") { Greeting(navController = navController) }
        composable(BottomBarScreen.Pet.route) { PetPage(navController = navController) }
        composable(BottomBarScreen.TaskList.route) { TaskPage(navController = navController) }
        composable(BottomBarScreen.StoryMap.route) { StoryMapPage(navController = navController) }
        composable("historypage") { HistoryPage(navController = navController) }
        /*TODO: get the selectedTaskIndex from caller*/
        composable("taskdetail") { TaskDetail(navController = navController, 1) }
        composable("taskpage") { TaskPage(navController = navController)}
        composable("readstory") { ReadStory(chapterIndex = 1, navController = navController)}
        composable("storymappage") { StoryMapPage(navController = navController)}
        composable("taskdetailhistory") { TaskDetailHistory(
            navController = navController,
            selectedTaskIndex = 1
        )}
    }
}