package com.example.myapplication


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.data.SolidUserData
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.theme.Rose0
import com.example.myapplication.ui.theme.Rose1
import com.example.myapplication.ui.theme.Yellow2


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
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val height = spacerInPetPage[count]
                Spacer(Modifier.height(height.dp))
                CatImage(count)
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(), horizontalAlignment = Alignment.CenterHorizontally
            ) {
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
fun ReactButton() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(), horizontalArrangement = Arrangement.SpaceAround
    ) {
        FloatingActionButton(
            onClick = { /*TODO*/ }, modifier = Modifier.padding(reactButtonDp),
            backgroundColor = Rose0, shape = CircleShape
        ) {
            Icon(imageVector = Icons.Default.CleaningServices, "Brush")
        }
        FloatingActionButton(
            onClick = { /*TODO*/ }, modifier = Modifier.padding(reactButtonDp),
            backgroundColor = Rose0, shape = CircleShape
        ) {
            Icon(imageVector = Icons.Default.Shower, "Shower")
        }
        FloatingActionButton(
            onClick = { /*TODO*/ }, modifier = Modifier.padding(reactButtonDp),
            backgroundColor = Rose0, shape = CircleShape
        ) {
            Icon(imageVector = Icons.Default.EmojiFoodBeverage, "Feed")
        }
    }

}

@Composable
private fun CatImage(count: Int) {
    val image = SolidUserData.pets[count].image
    Image(
        painter = painterResource(image), contentDescription = "Pet image in pet page",
        modifier = Modifier.size(300.dp)
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
    val amount = SolidUserData.pets.map { pet -> 1 }.sum()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(), horizontalArrangement = Arrangement.SpaceAround
    ) {

        if (count == 0) {
            IconButton(onClick = {}) {
                //Icon(imageVector = Icons.Filled.KeyboardArrowRight, null)
            }
        } else {
            IconButton(onClick = onIncrementMinus) {
                Icon(imageVector = Icons.Filled.KeyboardArrowLeft, null)
            }
        }
        Text(text = name, style = MaterialTheme.typography.h4, fontWeight = FontWeight.ExtraBold)
        IconButton(onClick = { showAlertDialog = true }) {
            Icon(imageVector = Icons.Filled.FolderOpen, "Pet details icon")
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
        if(showAlertDialog){
            AlertDialog(
                onDismissRequest = { showAlertDialog = false },
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center) {
                        Text("SCP-682", fontSize = 24.sp)
                        Image(
                            painter = painterResource(R.drawable.greeting_cat),
                            contentDescription = "Pet image in introduction",
                            modifier = Modifier.padding(30.dp,40.dp,0.dp,0.dp).size(150.dp)
                        )
                    }
                },
                text = {
                    Text("\n"+"\n"+"\n"+
                            "SCP-682 是隻大型、類似蜥蜴、來源不明之未知生物。該項目似乎極度聰明。SCP-682 似乎對所有生命表現出憎恨，此舉已反覆表現於收容期間之數次訪談。"+
                            "\n"+"SCP-682 持續被觀察到具有極高力量、速度、及反應力，但確切能力水平隨其外觀異動而變化。利用消耗能量或蛻皮，SCP-682 可快速改變其體型。" +
                            "SCP-682 可由攝入的任何物質中獲取能量，甚至是無機物。SCP-682 鼻孔內含之一組過濾鰓似乎有助於消化，可於任何溶液中擷取出有用物質，" +
                            "使其於強酸中仍可不斷再生。SCP-682 之再生與復原能力十分驚人，SCP-682 曾被觀察到於身體 87% 遭到損毀或腐爛之情況下持續移動及言語。"
                        , fontSize = 15.sp)
                },
                backgroundColor = Yellow2,
                contentColor = Color.Black,
                shape = RoundedCornerShape(8.dp),
                confirmButton = {}
            )
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