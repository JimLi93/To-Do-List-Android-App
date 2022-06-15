package com.example.myapplication.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.myapplication.ui.theme.Rose1
import com.example.myapplication.ui.theme.Rose2

@Composable
fun CardButton(navController: NavHostController, string: String,
                cardWidth: Int, cardHeight: Int, onClick: () -> Unit){
    var showAlertDialog by remember { mutableStateOf(false) }
    val alertString: String
    val uu: () -> Unit
    when (string) {
        "Complete" -> {
            uu = { showAlertDialog = true }
            alertString = "Please make sure you have complete this task."
        }
        "Delete" -> {
            uu = { showAlertDialog = true }
            alertString = "Please make sure you know the delete action. \n" +
                    "Your task will be delete forever."
        }
        else -> {
            uu = onClick
            alertString = ""
        }
    }
    if(showAlertDialog){
        AlertDialog(
            onDismissRequest = { showAlertDialog = false },
            title = { Text("Confirm", fontSize = 25.sp)},
            text = {Text(text = alertString, fontSize = 23.sp)
            },
            confirmButton = {
                when (string) {
                    "Complete" -> {
                        Row(Modifier.fillMaxWidth().padding(12.dp),
                            horizontalArrangement = Arrangement.SpaceAround,
                            verticalAlignment = Alignment.CenterVertically){
                            Button(onClick = onClick,
                                colors = ButtonDefaults.buttonColors(backgroundColor = Rose2)) {
                                Text(text = "I've finished.", fontSize = 20.sp)
                            }
                            Button(onClick = {showAlertDialog = false},
                                colors = ButtonDefaults.buttonColors(backgroundColor = Rose2)) {
                                Text(text = "Not yet finished.", fontSize = 20.sp)
                            }
                        }
                    }
                    "Delete" -> {
                        Row(Modifier.fillMaxWidth().padding(12.dp),
                            horizontalArrangement = Arrangement.SpaceAround,
                            verticalAlignment = Alignment.CenterVertically){
                            Button(onClick = onClick,
                                colors = ButtonDefaults.buttonColors(backgroundColor = Rose2)) {
                                Text(text = "I want to delete.", fontSize = 20.sp)
                            }
                            Button(onClick = {showAlertDialog = false},
                                colors = ButtonDefaults.buttonColors(backgroundColor = Rose2)) {
                                Text(text = "I will save it.", fontSize = 20.sp)
                            }
                        }
                    }
                    else -> {
                        Button(onClick = {showAlertDialog = false},
                            colors = ButtonDefaults.buttonColors(backgroundColor = Rose2)) {
                            Text(text = "OK", fontSize = 20.sp)
                        }
                    }
                }
            },

            backgroundColor = Rose1,
            contentColor = Color.Black,
            shape = RoundedCornerShape(8.dp)
        )
    }
    Card(shape = RoundedCornerShape(20.dp), backgroundColor = Rose2,
        modifier = Modifier
            .size(width = cardWidth.dp, height = cardHeight.dp)
            .clickable(onClick =  uu)
    ) {
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center){
            Text(text = string, style = MaterialTheme.typography.button,
                fontWeight = FontWeight.SemiBold)
        }
    }
}
