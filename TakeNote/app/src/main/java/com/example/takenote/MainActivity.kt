package com.example.takenote

import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.takenote.ui.theme.TakeNoteTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.takenote.viewModel.NoteViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TakeNoteTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   val noteViewModel = viewModel<NoteViewModel>()
                    Column(
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxWidth()
                    ) {
                        topBar()
                        mainForm()

                    }
                }
            }
        }
    }
}
@Composable
fun mainForm(){
    val title = remember {
        mutableStateOf("")
    }
    val content = remember {
        mutableStateOf("")
    }
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)
        , horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        TextField(value = title.value,
            modifier = Modifier.padding(5.dp),
            onValueChange = {
                text -> title.value = text
            },
            label = { Text(text = "Title")}
        )
        TextField(value = content.value,
            modifier = Modifier.padding(5.dp),
            onValueChange = {
                    text -> content.value = text
            },
            label = { Text(text = "Add a note")}
        )
        Button(onClick = {},
            elevation =  ButtonDefaults.buttonElevation(defaultElevation = 5.dp, pressedElevation = 0.5.dp),
            modifier = Modifier.padding(5.dp),
            shape = RoundedCornerShape(20.dp)
            ) {
                Text(text = "Save ")
        }
    }
}
@Composable
fun topBar(){
    androidx.compose.material3.Surface(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .height(50.dp),
        color = Color(0xcccff),
        shadowElevation = 5.dp
    ){
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.padding(start = 10.dp)
        ) {
            Text(text = "Note",
                fontSize = 18.sp,
                fontWeight = FontWeight.ExtraBold
                )
            Row (modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
                horizontalArrangement = Arrangement.End
                ){
                    androidx.compose.material3.Icon(imageVector = Icons.Filled.Notifications, contentDescription = "notification")
            }
        }
    }
}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TakeNoteTheme {
        Greeting("Android")
    }
}