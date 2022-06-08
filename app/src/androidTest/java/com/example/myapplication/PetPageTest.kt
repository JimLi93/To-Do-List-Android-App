package com.example.myapplication

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class PetPageTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    lateinit var navController: NavHostController

    @Test
    fun petPageDisplayTest() {
        composeTestRule.setContent {
            navController = rememberNavController()
            PetPage(navController)
        }
        composeTestRule.onNodeWithText("PET").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Pet page background").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Feed").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Shower").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Brush").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Pet image in pet page").assertIsDisplayed()
        composeTestRule.onNodeWithText("SCP-", true).assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Pet details icon").assertIsDisplayed()
    }

    // These tests will not be written until the implementation of data persistence
    //  fun switchPetTest
    //  fun petDetailsTest
    //  fun petReactTest

    @Test
    fun petPageNavigateToTaskPageTest() {
        composeTestRule.setContent {
            navController = rememberNavController()
            AppNavHost(navController)
            navController.navigate(BottomBarScreen.Pet.route)
        }
        composeTestRule.onNodeWithText("Tasks").performClick()
        // assert that the current page is the task page
        assertEquals(
            navController.currentBackStackEntry?.destination?.route,
            BottomBarScreen.TaskList.route
        )
    }

    @Test
    fun petPageNavigateToStoryMapPageTest() {
        composeTestRule.setContent {
            navController = rememberNavController()
            AppNavHost(navController)
            navController.navigate(BottomBarScreen.Pet.route)
        }
        composeTestRule.onNodeWithText("Story").performClick()
        // assert that the current page is the story map page
        assertEquals(
            navController.currentBackStackEntry?.destination?.route,
            BottomBarScreen.StoryMap.route
        )
    }
}