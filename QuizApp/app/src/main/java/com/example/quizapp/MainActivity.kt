package com.example.quizapp

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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.quizapp.model.Question
import com.example.quizapp.model.QuestionItem
import com.example.quizapp.ui.theme.QuizAppTheme
import com.example.quizapp.viewmodel.QuestionViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.net.ProxySelector

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuizAppTheme {
                val count = remember {
                    mutableStateOf(0)
                }
                val choiceSelected = remember {
                    mutableStateOf("")
                }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val questionViewModel = viewModel<QuestionViewModel>()
                    Column(
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        ScoreScreen(count = count , totalQuestion = questionViewModel.totalQuestionCount)
                        QuestionScreen(questionItem = questionViewModel.data.value.data!![count.value]);
                        ChoiceScreen(
                            count = count,
                            questionItem = questionViewModel.data.value.data!![count.value],
                            choiceSelected = choiceSelected
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ScoreScreen(count: MutableState<Int>, totalQuestion: Int){
    Row(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start ,
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(text = "Question ${count.value + 1}",
            fontSize = 30.sp ,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
        Text(text = "/${totalQuestion}",
            color = Color.Gray
        )
    }
}

@Composable
fun QuestionScreen(questionItem: QuestionItem){
    Text(
        text = questionItem.question,
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .height(180.dp),
        color = Color.White,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun ChoiceScreen(count: MutableState<Int>, questionItem: QuestionItem, choiceSelected: MutableState<String>){
    Column (
        modifier = Modifier
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        for (choice in questionItem.choices){
            if (choiceSelected.value.isNotEmpty() && choice == questionItem.answer){
                ChoiceButton(content = choice, choiceSelected = choiceSelected, color = Color.Green)
            }else if(choiceSelected.value.isNotEmpty() && choice != questionItem.answer){
                ChoiceButton(content = choice, choiceSelected = choiceSelected, color = Color.Red)
            }else{
                ChoiceButton(content = choice, choiceSelected = choiceSelected, color = Color.White)
            }
        }
        Button(onClick = {
            count.value++
            choiceSelected.value = ""
        },
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Magenta)
            ) {
                Text(text = "Next",
                    color = Color.White,
                    fontSize = 20.sp )
        }
    }
}

@Composable
fun ChoiceButton(content : String , choiceSelected : MutableState<String>, color: Color){
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(selected = content == choiceSelected.value,
            onClick = {
                if (choiceSelected.value.isEmpty()){
                    choiceSelected.value = content
                }
        })
        Text(text = content , color = color)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    QuizAppTheme {
    }
}