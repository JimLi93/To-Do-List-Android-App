package com.example.myapplication

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.lifecycle.Lifecycle.State
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
    val composeTestRule = createAndroidComposeRule<MainActivity>()
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

    @Test
    fun taskHistoryBackstackTest() {
        // Click the history button
        composeTestRule.onNode(
            hasClickAction() and isEnabled() and hasAnySibling(hasTextExactly("TASK"))
        ).performClick()
        // Click the back arrow
        composeTestRule.onNode(
            hasClickAction() and isEnabled() and hasAnySibling(hasTextExactly("HISTORY"))
        ).performClick()
        // Press the back button
        uiDevice.pressBack()
        assertTrue(navController.currentBackStackEntry?.destination?.route != "historypage")
    }

    @Test
    fun taskDetailsHistoryBackstackTest() {
        // Click the history button
        composeTestRule.onNode(
            hasClickAction() and isEnabled() and hasAnySibling(hasTextExactly("TASK"))
        ).performClick()
        composeTestRule.onNodeWithText("Task1").performClick()
        // Click the back arrow
        composeTestRule.onNode(
            hasClickAction() and isEnabled() and hasAnySibling(hasTextExactly("DETAILS"))
        ).performClick()
        // Press the back button
        uiDevice.pressBack()
        // Check that the current screen is not a task details in history
        assertFalse(
            navController.currentBackStackEntry?.destination?.route?.startsWith("taskdetailhistory")
                ?: false
        )
    }

    // fun addTaskBackstackTest
    // fun editTaskBackstackTest
    // fun deleteTaskBackstackTest
    // fun deleteTaskHistoryBackstackTest
    // fun completeTaskBackstackTest
    // fun swipeTaskBackstackTest

    @Test
    fun readStoryBackstackTest() {
        composeTestRule.onNode(hasClickAction(), true).performClick()
        composeTestRule.onNodeWithText("Story").performClick()
        composeTestRule.onNodeWithText("1-1").performClick()
        // Click the back arrow
        composeTestRule.onNode(
            hasClickAction() and isEnabled() and hasAnySibling(hasTextExactly("Chapter1-1"))
        ).performClick()
        // Press the back button
        uiDevice.pressBack()
        // Check that the current screen is not a story read
        assertFalse(
            navController.currentBackStackEntry?.destination?.route?.startsWith("readstory")
                ?: false
        )
    }

    @Test
    fun storyMapBackstackTest() {
        val composeLifecycle = composeTestRule.activity.lifecycle
        composeTestRule.onNode(hasClickAction(), true).performClick()
        composeTestRule.onNodeWithText("Story").performClick()
        // Click the chapter button
        composeTestRule.onNode(
            hasClickAction() and isEnabled() and hasAnySibling(hasTextExactly("STORY MAP"))
        ).performClick()
        // Select chapter 1
        composeTestRule.onNodeWithText("1").performClick()
        // Click the back arrow
        composeTestRule.onNode(
            hasClickAction() and isEnabled() and hasAnySibling(hasTextExactly("Chapter1-1"))
        ).performClick()
        // Press the back button
        uiDevice.pressBack()
        Thread.sleep(1500)
        // TODO: the name of the destinations to check
        val isAtStoryPage =
            navController.currentBackStackEntry?.destination?.route==BottomBarScreen.StoryMap.route
        assertFalse(isAtStoryPage && composeLifecycle.currentState != State.DESTROYED)
    }
}