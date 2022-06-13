package com.example.myapplication

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults.IconSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.myapplication.ui.theme.Rose2
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp

@Composable
fun TopHeader(string :String = "ERROR",
              trophy: Boolean = false,
              backArrow: Boolean = false,
              index: Boolean = false,
              navController: NavHostController,
              backArrowDestination: String = "greeting",
              trophyDestination: String = "greeting") {
    Surface(color = Rose2){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                enabled = backArrow,
                onClick = {
                        navController.navigate(backArrowDestination) {
                            popUpTo(navController.graph.findStartDestination().id)
                            launchSingleTop = true
                        }
                },
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .size(iconSize)
            ) {
                if(backArrow) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = null)
                }
                else if(index) {
                    Icon(Icons.Filled.List, contentDescription = null)
                }
            }
            Text(
                text = string,
                modifier = Modifier.align(Alignment.CenterVertically),
                style = MaterialTheme.typography.h2
            )
            IconButton(
                enabled = trophy,
                onClick = {
                    navController.navigate(trophyDestination) {
                        popUpTo(navController.graph.findStartDestination().id)
                        launchSingleTop = true
                    }
                },
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .size(iconSize)
            ) {
                if(trophy) {
                    Icon(Icons.Filled.Sort, contentDescription = null)
                }
            }
        }
    }
}


@Composable
fun NavBar(navController: NavHostController){
    val screens = listOf(
        BottomBarScreen.Pet,
        BottomBarScreen.TaskList,
        BottomBarScreen.StoryMap
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation (backgroundColor = Rose2){
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
){
    BottomNavigationItem(
        label = {
            Text(text = screen.title, style = MaterialTheme.typography.subtitle2)
        },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = "NavigationIcon"
            )
        },
        selected = currentDestination?.hierarchy?.any{
            it.route == screen.route
        } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.route){
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )

}


private val iconSize = 60.dp