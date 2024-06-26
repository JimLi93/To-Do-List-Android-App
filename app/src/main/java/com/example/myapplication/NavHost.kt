package com.example.myapplication

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.asLiveData
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.myapplication.data.PetDataStore
import com.example.myapplication.ui.TaskViewModel

@Composable
fun AppNavHost(
    context: Context,
    navController: NavHostController,
    viewModel: TaskViewModel,
    petPreference: PetDataStore,
    modifier: Modifier = Modifier
) {
    val petIndex = petPreference.preferenceFlow.asLiveData()
    NavHost(
        navController = navController,
        startDestination = "greeting",
        modifier = modifier
    ) {
        composable("addtask") { AddTask(navController = navController, viewModel = viewModel) }
        composable("greeting") { Greeting(navController = navController, petIndex = petIndex) }
        composable("tasklist") {
            TaskPage(navController = navController, taskList = viewModel.allTasks)
        }
        composable(BottomBarScreen.Pet.route) {
            PetPage(
                context = context,
                navController = navController,
                petPreference = petPreference,
                petIndex = petIndex
            )
        }
        composable(BottomBarScreen.TaskList.route) {
            TaskPage(navController = navController, taskList = viewModel.allTasks)
        }
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
        composable("historypage") {
            HistoryPage(navController = navController, historyList = viewModel.allHistoryTasks)
        }
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
                viewModel = viewModel,
                selectedTaskIndex = backStackEntry.arguments!!.getInt("taskindex")
            )
        }
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
        composable(
            route = "storymappage/{chapterindex}",
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
            route = "taskdetailhistory/{taskindex}",
            arguments = listOf(
                navArgument("taskindex") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            TaskDetailHistory(
                navController = navController,
                viewModel = viewModel,
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
                viewModel = viewModel,
                selectedTaskIndex = backStackEntry.arguments!!.getInt("taskindex")
            )
        }
    }
}