package com.example.myapplication


import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.theme.Rose1
import com.example.myapplication.ui.theme.Rose2

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.myapplication.data.UserData
import java.time.Year
import java.util.*

@Composable
fun EditTask(
    navController: NavHostController,
    selectedTaskIndex: Int
) {
    Scaffold(
        topBar = {
            TopHeader(
                string = "EDIT TASK",
                trophy = false,
                backArrow = true,
                index = false,
                navController = navController,
                backArrowDestination = BottomBarScreen.TaskList.route,
            )
        },
        backgroundColor = Rose1
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val data = UserData.tasks[selectedTaskIndex]
            EditString("Task name", data.name)
            //Spacer(Modifier.height(DefaultPadding))
            Text("Due Date", modifier = Modifier.padding(12.dp))
            SelectDateEdit(data.year, data.month, data.date)
            Text("Due Time", modifier = Modifier.padding(12.dp))
            SelectTimeEdit(data.hour, data.minute)
            Spacer(Modifier.height(DefaultPadding))
            EditString("Details", data.details)
            Spacer(Modifier.height(DefaultPadding * 2))
            Card(shape = RectangleShape, backgroundColor = Rose2,
                /* TODO: store the task before back to "edittask" */
                modifier = Modifier
                    .clickable(onClick = {navController.navigate(BottomBarScreen.TaskList.route)})
            ) {
                Text(text = "Save")
            }
        }
    }

}

@Composable
fun EditString(title: String, inputString: String) {
    var string by rememberSaveable { mutableStateOf(inputString) }
    StatelessAddString(string = string, onStringChange = { string = it }, title)
}

@Composable
fun SelectDateEdit(year: Int, month: Int, date: Int){

    // Fetching the Local Context
    val mContext = LocalContext.current

    // Declaring integer values
    // for year, month and day
    val mYear = year
    val mMonth = month
    val mDay = date

    // Initializing a Calendar
    val mCalendar = Calendar.getInstance()

    // Fetching current year, month and day
    //mYear = mCalendar.get(Calendar.YEAR)
    //mMonth = mCalendar.get(Calendar.MONTH)
    //mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

    mCalendar.time = Date()

    // Declaring a string value to
    // store date in string format
    val mDate = remember { mutableStateOf("$mYear/$mMonth/$mDay") }

    // Declaring DatePickerDialog and setting
    // initial values as current values (present year, month and day)
    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _, mYear, mMonth, mDay ->
            mDate.value = "$mYear/${mMonth+1}/${mDay}"
        }, mYear, mMonth, mDay
    )

    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {

        // Creating a button that on
        // click displays/shows the DatePickerDialog
        Button(onClick = {
            mDatePickerDialog.show()
        }, colors = ButtonDefaults.buttonColors(backgroundColor = Rose2) ) {
            Text(text = "Select a date", color = Color.Black)
        }

        // Adding a space of 100dp height
        //Spacer(modifier = Modifier.size(50.dp))

        // Displaying the mDate value in the Text
        Text(text = "${mDate.value}", fontSize = 30.sp, textAlign = TextAlign.Center)
    }
}

@Composable
fun SelectTimeEdit(hour: Int, minute: Int){

    // Fetching the Local Context
    val mContext = LocalContext.current

    val mHour = hour
    val mMinute = minute
    // Initializing a Calendar
    val mCalendar = Calendar.getInstance()

    // Fetching current year, month and day
    //mHour = mCalendar.get(Calendar.HOUR_OF_DAY)
    //mMinute = mCalendar.get(Calendar.MINUTE)

    mCalendar.time = Date()

    // Declaring a string value to
    // store date in string format
    val mTime = remember { mutableStateOf("$mHour/$mMinute") }

    // Declaring DatePickerDialog and setting
    // initial values as current values (present year, month and day)
    val mDatePickerDialog = TimePickerDialog(
        mContext,
        { _, mHour, mMinute ->
            mTime.value = "$mHour/$mMinute"
        }, mHour, mMinute, false
    )

    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {

        // Creating a button that on
        // click displays/shows the DatePickerDialog
        Button(onClick = {
            mDatePickerDialog.show()
        }, colors = ButtonDefaults.buttonColors(backgroundColor = Rose2) ) {
            Text(text = "Select time", color = Color.Black)
        }

        // Adding a space of 100dp height
        //Spacer(modifier = Modifier.size(50.dp))

        // Displaying the mDate value in the Text
        Text(text = "${mTime.value}", fontSize = 30.sp, textAlign = TextAlign.Center)
    }
}

@Preview
@Composable
fun EditTaskPreview() {
    MyApplicationTheme {
        val navController = rememberNavController()
        EditTask(navController, 0)
    }
}

private val DefaultPadding = 20.dp
