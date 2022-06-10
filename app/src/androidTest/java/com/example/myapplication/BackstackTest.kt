package com.example.myapplication

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.uiautomator.UiDevice
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class BackstackTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    lateinit var navController: NavHostController
    private lateinit var uiDevice: UiDevice

    @Before
    fun setupUiDevice() {
        composeTestRule.setContent {
            navController = rememberNavController()
            AppNavHost(navController)
        }
        uiDevice = UiDevice.getInstance(getInstrumentation())
    }

    // TODO: These tests still fail if some task names coincide with bottom navigation titles
    //  so fake data are needed to avoid false negatives
    @Test
    fun petPageToTaskListPageBackstackTest() {
        composeTestRule.onNode(hasClickAction(), true).performClick()

        composeTestRule.onNodeWithText(BottomBarScreen.StoryMap.title).performClick()
        composeTestRule.onNodeWithText(BottomBarScreen.TaskList.title).performClick()
        composeTestRule.onNodeWithText(BottomBarScreen.StoryMap.title).performClick()
        composeTestRule.onNodeWithText(BottomBarScreen.TaskList.title).performClick()
        composeTestRule.onNodeWithText(BottomBarScreen.Pet.title).performClick()
        composeTestRule.onNodeWithText(BottomBarScreen.TaskList.title).performClick()

        uiDevice.pressBack()

        assertTrue(
            navController.currentBackStackEntry?.destination?.route != BottomBarScreen.Pet.route
        )
    }

    @Test
    fun petPageToStoryMapPageBackstackTest() {
        composeTestRule.onNode(hasClickAction(), true).performClick()

        composeTestRule.onNodeWithText(BottomBarScreen.StoryMap.title).performClick()
        composeTestRule.onNodeWithText(BottomBarScreen.TaskList.title).performClick()
        composeTestRule.onNodeWithText(BottomBarScreen.Pet.title).performClick()
        composeTestRule.onNodeWithText(BottomBarScreen.TaskList.title).performClick()
        composeTestRule.onNodeWithText(BottomBarScreen.Pet.title).performClick()
        composeTestRule.onNodeWithText(BottomBarScreen.StoryMap.title).performClick()

        uiDevice.pressBack()

        assertTrue(
            navController.currentBackStackEntry?.destination?.route != BottomBarScreen.Pet.route
        )
    }

    @Test
    fun taskListPageToPetPageBackstackTest() {
        composeTestRule.onNode(hasClickAction(), true).performClick()

        composeTestRule.onNodeWithText(BottomBarScreen.StoryMap.title).performClick()
        composeTestRule.onNodeWithText(BottomBarScreen.TaskList.title).performClick()
        composeTestRule.onNodeWithText(BottomBarScreen.Pet.title).performClick()
        composeTestRule.onNodeWithText(BottomBarScreen.StoryMap.title).performClick()
        composeTestRule.onNodeWithText(BottomBarScreen.TaskList.title).performClick()
        composeTestRule.onNodeWithText(BottomBarScreen.Pet.title).performClick()

        uiDevice.pressBack()

        assertTrue(
            navController.currentBackStackEntry?.destination?.route != BottomBarScreen.TaskList.route
        )
    }

    @Test
    fun taskListPageToStoryMapPageBackstackTest() {
        composeTestRule.onNode(hasClickAction(), true).performClick()

        composeTestRule.onNodeWithText(BottomBarScreen.Pet.title).performClick()
        composeTestRule.onNodeWithText(BottomBarScreen.TaskList.title).performClick()
        composeTestRule.onNodeWithText(BottomBarScreen.StoryMap.title).performClick()
        composeTestRule.onNodeWithText(BottomBarScreen.Pet.title).performClick()
        composeTestRule.onNodeWithText(BottomBarScreen.TaskList.title).performClick()
        composeTestRule.onNodeWithText(BottomBarScreen.StoryMap.title).performClick()

        uiDevice.pressBack()

        assertTrue(
            navController.currentBackStackEntry?.destination?.route != BottomBarScreen.TaskList.route
        )
    }

    @Test
    fun storyMapPageToPetPageBackstackTest() {
        composeTestRule.onNode(hasClickAction(), true).performClick()

        composeTestRule.onNodeWithText(BottomBarScreen.StoryMap.title).performClick()
        composeTestRule.onNodeWithText(BottomBarScreen.Pet.title).performClick()
        composeTestRule.onNodeWithText(BottomBarScreen.StoryMap.title).performClick()
        composeTestRule.onNodeWithText(BottomBarScreen.TaskList.title).performClick()
        composeTestRule.onNodeWithText(BottomBarScreen.StoryMap.title).performClick()
        composeTestRule.onNodeWithText(BottomBarScreen.Pet.title).performClick()

        uiDevice.pressBack()

        assertTrue(
            navController.currentBackStackEntry?.destination?.route != BottomBarScreen.StoryMap.route
        )
    }

    @Test
    fun storyMapPageToTaskListPageBackstackTest() {
        composeTestRule.onNode(hasClickAction(), true).performClick()

        composeTestRule.onNodeWithText(BottomBarScreen.Pet.title).performClick()
        composeTestRule.onNodeWithText(BottomBarScreen.TaskList.title).performClick()
        composeTestRule.onNodeWithText(BottomBarScreen.StoryMap.title).performClick()
        composeTestRule.onNodeWithText(BottomBarScreen.TaskList.title).performClick()
        composeTestRule.onNodeWithText(BottomBarScreen.StoryMap.title).performClick()
        composeTestRule.onNodeWithText(BottomBarScreen.TaskList.title).performClick()

        uiDevice.pressBack()

        assertTrue(
            navController.currentBackStackEntry?.destination?.route != BottomBarScreen.StoryMap.route
        )
    }

    @Test
    fun taskDetailsBackstackTest() {
        composeTestRule.onNode(hasClickAction(), true).performClick()
        composeTestRule.onNodeWithText("Task1").performClick()
        // Click the back arrow
        composeTestRule.onNode(
            hasClickAction() and isEnabled() and hasAnySibling(hasTextExactly("DETAILS"))
        ).performClick()
        // Press the back button
        uiDevice.pressBack()
        // Check that the current screen is not a task detail
        assertFalse(
            navController.currentBackStackEntry?.destination?.route?.startsWith("taskdetail")
                ?: false
        )
    }
}