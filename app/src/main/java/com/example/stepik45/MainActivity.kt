package com.example.stepik45

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stepik45.ui.theme.Stepik45Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Stepik45Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface(
                        modifier = Modifier.padding(innerPadding),
                        color = Color.White
                    ) {
                        val buttonList = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "Back", "0", "OK")
                        var pinCode by remember {
                            mutableStateOf("")
                        }
                        var pinCodeColor by remember {
                            mutableStateOf(Color.Black)
                        }
                        var pinCodeTrue by remember {
                            mutableStateOf(false)
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(50.dp, 0.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Введите 4 значный код"
                            )
                            Spacer(
                                modifier = Modifier.height(50.dp)
                            )
                            Text(
                                text = pinCode,
                                color = pinCodeColor
                            )
                            Spacer(
                                modifier = Modifier.height(30.dp)
                            )
                            LazyVerticalGrid(
                                columns = GridCells.Fixed(3),
                                horizontalArrangement = Arrangement.spacedBy(15.dp)
                            ) {
                                items(buttonList) {
                                    if (it == "OK") {
                                        Button(onClick = {
                                            pinCodeColor = checkingCombination(item = it, variable = pinCode)
                                            if(pinCodeColor == Color.Green) {
                                                pinCodeTrue = true
                                            }
                                        }) {
                                            Text(text = it)
                                        }
                                    } else {
                                        OutlinedButton(onClick = {
                                            pinCode = getСombinations(it, pinCode)
                                            if(it == "Back") {
                                                pinCodeColor = checkingCombination(item = it, variable = pinCode)
                                                if(pinCodeColor == Color.Green) {
                                                    pinCodeTrue = true
                                                }
                                            }
                                        }) {
                                            Text(text = it)
                                        }
                                    }
                                }
                            }
                        }
                        if (pinCodeTrue) {
                            AlertDialog(
                                onDismissRequest = { pinCodeTrue = false},
                                text = { Text(text = "Вы вошли у учётную запись") },
                                confirmButton = {
                                    Button(onClick = {
                                        pinCodeTrue = false
                                        pinCode = ""
                                        pinCodeColor = Color.Black
                                    }) {
                                        Text("Попробовать заново")
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

fun getСombinations(
    item: String,
    variable: String,
): String {
    var combinationUpdate = variable

    if(variable.length <= 3) {
        when(item) {
            "1" -> combinationUpdate+="1"
            "2" -> combinationUpdate+="2"
            "3" -> combinationUpdate+="3"
            "4" -> combinationUpdate+="4"
            "5" -> combinationUpdate+="5"
            "6" -> combinationUpdate+="6"
            "7" -> combinationUpdate+="7"
            "8" -> combinationUpdate+="8"
            "9" -> combinationUpdate+="9"
            "0" -> combinationUpdate+="0"
            "Back" -> {
                if (combinationUpdate.isEmpty()) {
                    combinationUpdate = ""
                } else {
                    val combinationBack = combinationUpdate.substring(0, combinationUpdate.length - 1)
                    combinationUpdate = combinationBack
                }
            }
        }
        return combinationUpdate
    } else if(variable.length == 4) {
        if (item =="Back") {
            val combinationBack = combinationUpdate.substring(0, combinationUpdate.length - 1)
            combinationUpdate = combinationBack
        }
        return combinationUpdate
    } else {
        return combinationUpdate
    }
}

fun checkingCombination(
    item: String,
    variable: String
): Color {
    if (item == "OK") {
        if(variable == "1111") {
            return Color.Green
        }
        else {
            return Color.Red
        }
    } else if(item == "Back") {
        return Color.Black
    } else {
        return Color.Black
    }
}