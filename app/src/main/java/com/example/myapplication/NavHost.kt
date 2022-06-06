package com.example.myapplication

import android.provider.ContactsContract
import android.service.autofill.UserData
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = "1",
        modifier = modifier
    ) {
        composable("1") { ReadStory(chapterIndex = 0) }
        composable("greeting") { Greeting(navController) }
        composable(BottomBarScreen.Pet.route) { PetPage(navController)  }
        composable(BottomBarScreen.TaskList.route) { TaskPage(navController)  }
        composable(BottomBarScreen.StoryMap.route) { StoryMapPage(navController)  }
        /*
        composable(Overview.name) {
            OverviewBody(
                onClickSeeAllAccounts = { navController.navigate(Accounts.name) },
                onClickSeeAllBills = { navController.navigate(Bills.name) },
                onAccountClick = { name ->
                    navigateToSingleAccount(navController, name)
                },
            )
        }
        composable(Accounts.name) {
            AccountsBody(accounts = UserData.accounts) { name ->
                navigateToSingleAccount(navController = navController, accountName = name)
            }
        }
        composable(Bills.name) {
            BillsBody(bills = UserData.bills)
        }
     */
    }
}