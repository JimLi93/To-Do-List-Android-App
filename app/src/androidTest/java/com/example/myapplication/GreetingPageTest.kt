package com.example.myapplication

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
// @RunWith(AndroidJUnit4::class)
class GreetingPageTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    lateinit var navController: NavHostController

    @Test
    fun greetingPageDisplayTest() {
        composeTestRule.setContent {
            navController = rememberNavController()
            Greeting(navController)
        }
        composeTestRule.onNodeWithText("23:59").assertIsDisplayed()
        // TODO("Test the existence of the pet")
        composeTestRule.onNodeWithText("Welcome").assertIsDisplayed()
        composeTestRule.onNodeWithText("back").assertIsDisplayed()
        composeTestRule.onNodeWithText("-Tap to Continue-").assertIsDisplayed()
    }

    @Test
    fun clickGreetingPageTest() {
        composeTestRule.setContent {
            navController = rememberNavController()
            AppNavHost(navController)
            navController.navigate("greeting")
        }
        composeTestRule.onNode(hasClickAction(), true).performClick()
        // assert that the current page is the task page
        assertEquals(
            navController.currentBackStackEntry?.destination?.route,
            BottomBarScreen.TaskList.route
        )
    }
}