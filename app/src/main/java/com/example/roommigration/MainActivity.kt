package com.example.roommigration

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.roommigration.database.model.User
import com.example.roommigration.ui.main.MainViewModel
import com.example.roommigration.ui.theme.RoomMIgrationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RoomMIgrationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen(viewModel)
                }
            }
        }
    }
}

@Composable
fun MainScreen(viewModel: MainViewModel) {
    val name = remember {
        mutableStateOf("")
    }
    val age = remember {
        mutableStateOf("")
    }
    val list = viewModel.list.value
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        list.forEach {
            Text(
                text = "${it.id} ---> ${it.name} ",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                textAlign = TextAlign.Center
            )
        }

        OutlinedTextField(value = name.value, onValueChange = { name.value = it })
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(value = age.value, onValueChange = { age.value = it })
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedButton(onClick = {
            viewModel.insertUser(
                User(id = 0, name = name.value)
            )
            name.value = ""
            age.value=""
        }) {
            Text(text = "Save User")
        }


    }


}