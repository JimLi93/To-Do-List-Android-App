package com.example.myapplication

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.myapplication.ui.TaskViewModel

@Composable
fun AppNavHost(
    navController: NavHostController,
    viewModel: TaskViewModel,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = "greeting",
        modifier = modifier
    ) {
        composable("addtask") { AddTask(navController = navController, viewModel = viewModel) }
        composable("greeting") { Greeting(navController = navController) }
        composable(BottomBarScreen.Pet.route) { PetPage(navController = navController) }
        composable(BottomBarScreen.TaskList.route) { TaskPage(navController = navController, taskList = viewModel.allTasks) }
        composable(
            BottomBarScreen.StoryMap.route +"/{chapterindex}",
            arguments = listOf(
                navArgument("chapterindex") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            StoryMapPage(
                navController = navController,
                chapterIndex = backStackEntry.arguments!!.getInt("chapterindex")
            )
        }
        composable(
            BottomBarScreen.StoryMap.route
        ) {
            StoryMapPage(
                navController = navController,1
            )
        }
        composable("historypage") { HistoryPage(navController = navController) }
        composable(
            route = "taskdetail/{taskindex}",
            arguments = listOf(
                navArgument("taskindex") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val selectedTaskIndex = backStackEntry.arguments!!.getInt("taskindex")
            TaskDetail(
                navController = navController,
                // THIS DOES NOT WORK
                //  data = viewModel.retrieveAllTasks().value!![0],
                // THIS WORKS
                data = viewModel.allTasks.value!![0],
                selectedTaskIndex = selectedTaskIndex
            )
        }
        //composable("taskpage") { TaskPage(navController = navController)}
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
        //composable("storymappage") { StoryMapPage(navController = navController)}
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