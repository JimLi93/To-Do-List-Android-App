package com.example.myapplication


import android.widget.ImageButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.data.SolidUserData
import com.example.myapplication.data.UserData
import com.example.myapplication.ui.theme.*
import java.nio.file.Files.size


@Composable
fun PetPage(navController: NavHostController) {
    Scaffold(
        topBar = { TopHeader("PET", false, false, false) },
        bottomBar = { NavBar(navController) },
        backgroundColor = Rose1
    ) { innerPadding ->
        Surface(
            modifier = Modifier.padding(0.dp)
        ) {
            Image(painter = painterResource(R.drawable.petbackground), contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(), contentScale = ContentScale.FillHeight,
            alpha = 0.7f)
            ReactButton()
            var count by rememberSaveable { mutableStateOf(0) }
            Column (modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),horizontalAlignment = Alignment.CenterHorizontally){
                val height = spacerInPetPage[count]
                Spacer(Modifier.height(height.dp))
                CatImage(count)
            }
            Column (modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),horizontalAlignment = Alignment.CenterHorizontally){
                Spacer(Modifier.height(SwitchPetSpacer))
                SwitchPetBar(count = count,
                    onIncrementAdd = { count++ },
                    onIncrementMinus = { count-- }
                )
            }

        }
    }
}

@Composable
fun ReactButton(){
    Row(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(),horizontalArrangement = Arrangement.SpaceAround){
        FloatingActionButton(onClick = { /*TODO*/ }, modifier = Modifier.padding(reactButtonDp),
            backgroundColor = Rose0, shape = CircleShape
        ) {
            Icon(imageVector = Icons.Default.CleaningServices, null)
        }
        FloatingActionButton(onClick = { /*TODO*/ }, modifier = Modifier.padding(reactButtonDp),
            backgroundColor = Rose0, shape = CircleShape
        ) {
            Icon(imageVector = Icons.Default.Shower, null)
        }
        FloatingActionButton(onClick = { /*TODO*/ }, modifier = Modifier.padding(reactButtonDp),
            backgroundColor = Rose0, shape = CircleShape
        ) {
            Icon(imageVector = Icons.Default.EmojiFoodBeverage, null)
        }
    }

}

@Composable
private fun CatImage(count: Int){
    val  image = SolidUserData.pets[count].image
    Image(painter = painterResource(image), contentDescription = null,
    modifier = Modifier.size(300.dp))
}

@Composable
fun SwitchPetBar(
    onIncrementAdd: () -> Unit,
    onIncrementMinus: () -> Unit,
    count: Int){
    val name = SolidUserData.pets[count].name
    val amount = SolidUserData.pets.map { pet ->  1 }.sum()
    Row(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(),horizontalArrangement = Arrangement.SpaceAround){

        if (count == 0) {
            IconButton(onClick = {}) {
                //Icon(imageVector = Icons.Filled.KeyboardArrowRight, null)
            }
        } else {
            IconButton(onClick = onIncrementMinus) {
                Icon(imageVector = Icons.Filled.KeyboardArrowLeft, null)
            }
        }
        Text(text = name,style = MaterialTheme.typography.h4, fontWeight = FontWeight.ExtraBold)
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Filled.FolderOpen, null)
        }
        if (count == amount - 1) {
            IconButton(onClick = {}) {
                //Icon(imageVector = Icons.Filled.KeyboardArrowLeft, null)
            }
        } else {
            IconButton(onClick = onIncrementAdd) {
                Icon(imageVector = Icons.Filled.KeyboardArrowRight, null)
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
private val spacerInPetPage : List<Int> = listOf(300,250)
private val SwitchPetSpacer = 550.dp