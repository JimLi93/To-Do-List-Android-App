package com.example.myapplication


import android.service.autofill.OnClickAction
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.components.FAB
import com.example.myapplication.data.SolidUserData
import com.example.myapplication.ui.theme.*


@Composable
fun PetPage(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopHeader(
                string = "PET",
                navController = navController
            )
        },
        bottomBar = { NavBar(navController) },
        backgroundColor = Rose1
    ) { innerPadding ->
        Surface(
            modifier = Modifier.padding(0.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.petbackground),
                contentDescription = "Pet page background",
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(), contentScale = ContentScale.FillHeight,
                alpha = 0.7f
            )
            ReactButton()
            var count by rememberSaveable { mutableStateOf(0) }
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Center
            ) {
                Column {
                    CatImage(count)
                    Spacer(Modifier.height(50.dp))
                }

            }
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Center
            ) {
                SwitchPetBar(
                    count = count,
                    onIncrementAdd = { count++ },
                    onIncrementMinus = { count-- }
                )

            }

        }
    }
}


@Composable
fun ReactButton() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(), horizontalArrangement = Arrangement.SpaceAround
    ) {
        FAB(Icons.Default.CleaningServices, Rose0, 80, 16, { /*TODO*/ }, "Brush")
        FAB(Icons.Default.Shower, Rose0, 80, 16, { /*TODO*/ }, "Shower")
        FAB(Icons.Default.EmojiFoodBeverage, Rose0, 80, 16, { /*TODO*/ }, "Feed")
    }

}

@Composable
private fun CatImage(count: Int) {
    val image = SolidUserData.pets[count].image
    Image(
        painter = painterResource(image), contentDescription = "Pet image in pet page",
        modifier = Modifier.size(250.dp)
    )
}

@Composable
fun SwitchPetBar(
    onIncrementAdd: () -> Unit,
    onIncrementMinus: () -> Unit,
    count: Int
) {
    var showAlertDialog by remember { mutableStateOf(false) }
    val name = SolidUserData.pets[count].name
    val details = SolidUserData.pets[count].detail
    val image = SolidUserData.pets[count].image
    val amount = SolidUserData.pets.map { pet -> 1 }.sum()
    Box(){
        Row(
            modifier = Modifier
                .height(bottomHeight)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = name,
                modifier = Modifier.padding(horizontal = 12.dp),
                fontSize = petNameSize,
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.ExtraBold
            )
        }
        Row(
            modifier = Modifier
                .height(bottomHeight)
                .fillMaxWidth()
                .padding(horizontal = arrowPadding),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            if (count == 0) {
                IconButton(onClick = {}) {
                    //Icon(imageVector = Icons.Filled.KeyboardArrowRight, null
                // , modifier = Modifier.size(arrowSize))
                }
            } else {
                IconButton(onClick = onIncrementMinus) {
                    Icon(painter = painterResource(R.drawable.leftarrow), null
                        , modifier = Modifier.size(arrowSize))
                }
            }
            if (count == amount - 1) {
                IconButton(onClick = {}) {
                    //Icon(imageVector = Icons.Filled.KeyboardArrowLeft, null
                // , modifier = Modifier.size(arrowSize))
                }
            } else {
                IconButton(onClick = onIncrementAdd) {
                    Icon(painter = painterResource(R.drawable.rightarrow), null
                        , modifier = Modifier.size(arrowSize))
                }
            }
        }
        Row(
            modifier = Modifier
                .height(bottomHeight)
                .fillMaxWidth()
                .padding(horizontal = folderPadding),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {}) {
                //Icon(imageVector = Icons.Filled.FolderShared, "Pet details icon"
            // , modifier = Modifier.size(folderSize))
            }
            IconButton(onClick = { showAlertDialog = true }) {
                Icon(imageVector = Icons.Filled.FolderShared, "Pet details icon"
                    , modifier = Modifier.size(folderSize))
            }
            if (showAlertDialog) {
                AlertDialog(
                    onDismissRequest = { showAlertDialog = false },
                    title = {
                        Row(
                            modifier = Modifier
                                .height(500.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(name, fontSize = 24.sp)
                            Image(
                                painter = painterResource(image),
                                contentDescription = "Pet image in introduction",
                                modifier = Modifier
                                    .padding(top = 50.dp)
                                    .size(100.dp)
                            )
                        }
                    },
                    modifier = Modifier
                        .padding(20.dp)
                    ,
                    text = {
                        Text(
                            details,
                            fontSize = 15.sp
                        )
                    },
                    backgroundColor = Rose1,
                    contentColor = Color.Black,
                    shape = RoundedCornerShape(8.dp),
                    confirmButton = {
                        Button(onClick = {showAlertDialog = false},
                            colors = ButtonDefaults.buttonColors(backgroundColor = Rose2)) {
                            Text(text = "OK", fontSize = 20.sp)
                        }
                    }
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PetPagePreview() {
    MyApplicationTheme {
        val navController = rememberNavController()
        PetPage(navController)
    }
}

private val reactButtonDp = 16.dp
private val spacerInPetPage: List<Int> = listOf(300, 250)
private val SwitchPetSpacer = 550.dp
private val bottomHeight = 80.dp
private val folderSize = 38.dp
private val folderPadding = 65.dp
private val arrowPadding = 20.dp
private val petNameSize = 50.sp
private val arrowSize = 45.dp

