package com.example.myapplication.components

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.Rose2
import java.util.*

@Composable
fun SelectDate(
    edit: Boolean,
    year: Int,
    month: Int,
    date: Int,
    onDateChange: (Int, Int, Int) -> Unit
) {

    // Fetching the Local Context
    val mContext = LocalContext.current

    // Declaring integer values
    // for year, month and day
    val mYear: Int
    val mMonth: Int
    val mDay: Int
    val string: String

    // Initializing a Calendar
    val mCalendar = Calendar.getInstance()

    if(edit){
        mYear = year
        mMonth = month - 1
        mDay = date
        string = "$year/$month/$date"
    }
    else {
        mYear = mCalendar.get(Calendar.YEAR)
        mMonth = mCalendar.get(Calendar.MONTH)
        mDay = mCalendar.get(Calendar.DAY_OF_MONTH)
        string = ""
    }

    // Fetching current year, month and day

    mCalendar.time = Date()

    // Declaring a string value to
    // store date in string format
    val mDate = remember { mutableStateOf(string) }

    // Declaring DatePickerDialog and setting
    // initial values as current values (present year, month and day)
    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _, newYear, newMonth, newDay ->
            mDate.value = "$newYear/${newMonth+1}/${newDay}"
            onDateChange(newYear, newMonth, newDay)
        }, mYear, mMonth, mDay
    )

    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {

        // Creating a button that on
        // click displays/shows the DatePickerDialog
        Button(onClick = { mDatePickerDialog.show() } , modifier = Modifier.width(width)
            , colors = ButtonDefaults.buttonColors(backgroundColor = Rose2)
        ) {
            Text(text = "Select date", color = Color.Black, style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold)
        }

        // Adding a space of 100dp height
        //Spacer(modifier = Modifier.size(50.dp))

        // Displaying the mDate value in the Text
        Text(text = mDate.value, textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body1)
    }
}

@Composable
fun SelectTime(edit: Boolean, hour: Int, minute: Int, onTimeChange: (Int, Int) -> Unit){

    // Fetching the Local Context
    val mContext = LocalContext.current

    val mHour: Int
    val mMinute: Int
    val string: String
    // Initializing a Calendar
    val mCalendar = Calendar.getInstance()

    // Fetching current year, month and day
    if(edit){
        mHour = hour
        mMinute = minute
        string = addZero(hour) + " : " + addZero(minute)
    }
    else {
        mHour = mCalendar.get(Calendar.HOUR_OF_DAY)
        mMinute = mCalendar.get(Calendar.MINUTE)
        string = ""
    }

    mCalendar.time = Date()

    // Declaring a string value to
    // store date in string format
    val mTime = remember { mutableStateOf(string) }

    // Declaring DatePickerDialog and setting
    // initial values as current values (present year, month and day)
    val mTimePickerDialog = TimePickerDialog(
        mContext,
        { _, newHour, newMinute ->
            mTime.value = "${addZero(newHour)} : ${addZero(newMinute)}"
            onTimeChange(newHour, newMinute)
        }, mHour, mMinute, false
    )

    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {

        // Creating a button that on
        // click displays/shows the DatePickerDialog
        Button(onClick = { mTimePickerDialog.show() } , modifier = Modifier.width(width)
            , colors = ButtonDefaults.buttonColors(backgroundColor = Rose2)
            ) {
            Text(text = "Select time", color = Color.Black, style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold
            )
        }

        // Adding a space of 100dp height
        //Spacer(modifier = Modifier.size(50.dp))

        // Displaying the mDate value in the Text
        Text(text = mTime.value, textAlign = TextAlign.Center
            ,style = MaterialTheme.typography.body1)
    }
}

@Composable
fun SelectItems(
    edit: Boolean,
    year: Int,
    month: Int,
    date: Int,
    hour: Int,
    minute: Int,
    onDateChange: (Int, Int, Int) -> Unit,
    onTimeChange: (Int, Int) -> Unit
) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly){
        Column (horizontalAlignment = Alignment.CenterHorizontally){
            Text("Due Date", modifier = Modifier.padding(12.dp),
                style = MaterialTheme.typography.body1)
            SelectDate(edit, year, month, date, onDateChange)
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally){
            Text("Due Time", modifier = Modifier.padding(12.dp),
                style = MaterialTheme.typography.body1)
            SelectTime(edit, hour, minute, onTimeChange)
        }
    }
}

private val width = 120.dp