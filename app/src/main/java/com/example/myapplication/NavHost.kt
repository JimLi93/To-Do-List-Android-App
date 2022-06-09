package com.example.myapplication

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavArgument
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

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
        composable(
            route = "taskdetail/{taskindex}",
            arguments = listOf(
                navArgument("taskindex") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            TaskDetail(
                navController = navController,
                selectedTaskIndex = backStackEntry.arguments!!.getInt("taskindex")
            )
        }
        composable("taskpage") { TaskPage(navController = navController)}
        composable(
            route = "readstory/{chapterindex}",
            arguments = listOf(
                navArgument("chapterindex") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            ReadStory(
                navController = navController,
                chapterIndex = backStackEntry.arguments!!.getInt("chapterindex")
            )
        }
        composable("storymappage") { StoryMapPage(navController = navController)}
        composable(
            route = "taskdetailhistory/{taskindex}",
            arguments = listOf(
                navArgument("taskindex") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            TaskDetailHistory(
                navController = navController,
                selectedTaskIndex = backStackEntry.arguments!!.getInt("taskindex")
            )
        }
        composable(
            route = "edittask/{taskindex}",
            arguments = listOf(
                navArgument("taskindex") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            EditTask(
                navController = navController,
                selectedTaskIndex = backStackEntry.arguments!!.getInt("taskindex")
            )
        }
    }
}