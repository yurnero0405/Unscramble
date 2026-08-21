package com.example.unscramble

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.example.unscramble.ui.theme.UnscrambleTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            UnscrambleTheme {
                GameScreen()
            }
        }
    }
}

@Composable
fun GameScreen() {

    // Stores what the user types
    var userAnswer by remember {
        mutableStateOf("")
    }

    // List of words
    val words = listOf(
        "CAT",
        "DOG",
        "BOOK"
    )

    // Keeps track of the current word
    var currentWordIndex by remember {
        mutableStateOf(0)
    }

    // The correct answer
    val correctAnswer = words[currentWordIndex]

    // The scrambled word shown to the player
    var scrambledWord by remember {
        mutableStateOf(
            words[0].shuffled().joinToString("")
        )
    }

    // Player's score
    var score by remember {
        mutableStateOf(0)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "UNSCRAMBLE",
            fontSize = 30.sp
        )

        // Display the scrambled word
        Text(
            text = scrambledWord,
            fontSize = 40.sp
        )

        Text(
            text = "Unscramble the word!"
        )

        OutlinedTextField(
            value = userAnswer,
            onValueChange = {
                userAnswer = it
            },
            label = {
                Text("Enter your answer")
            }
        )

        Button(
            onClick = {

                // Check if the answer is correct
                if (userAnswer == correctAnswer) {

                    // Increase score
                    score++

                    // Move to the next word
                    if (currentWordIndex < words.size - 1) {

                        currentWordIndex++

                        // Clear the answer field
                        userAnswer = ""

                        // Scramble the new word
                        scrambledWord = words[currentWordIndex]
                            .shuffled()
                            .joinToString("")
                    }
                }
            }
        ) {
            Text("SUBMIT")
        }

        Text(
            text = "Score: $score"
        )
    }
}