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


@Composable
fun TopHeader(string :String, trophy: Boolean, backArrow: Boolean, index: Boolean) {
    Surface(color = Rose2){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                onClick = {},
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .size(iconSize)
            ) {
                if(backArrow == true) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = null)
                }
                else if(index == true) {
                    Icon(Icons.Filled.List, contentDescription = null)
                }
                //Icon(Icons.Filled.ArrowBack, contentDescription = null)
            }
            Text(
                text = string,
                style = MaterialTheme.typography.h3,
                modifier = Modifier.align(Alignment.CenterVertically),
                fontWeight = FontWeight.ExtraBold
            )
            IconButton(
                onClick = { },
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .size(iconSize)
            ) {
                if(trophy == true) {
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
            Text(text = screen.title)
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