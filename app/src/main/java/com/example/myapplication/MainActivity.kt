package com.example.myapplication

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.data.DatabaseApplication
import com.example.myapplication.data.PetDataStore
import com.example.myapplication.ui.TaskViewModel
import com.example.myapplication.ui.TaskViewModelFactory
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    private val viewModel: TaskViewModel by viewModels {
        TaskViewModelFactory((application as DatabaseApplication).database.taskDao())
    }
    private lateinit var PetDataStore: PetDataStore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize PetDataStore
        PetDataStore = PetDataStore(this)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MyApp(this, viewModel, PetDataStore)
                }
            }
        }
    }
}

@Composable
fun MyApp(context: Context, viewModel: TaskViewModel, petPreference: PetDataStore) {
    val navController = rememberNavController()
    AppNavHost(
        context = context,
        navController = navController,
        viewModel = viewModel,
        petPreference = petPreference
    )
}

