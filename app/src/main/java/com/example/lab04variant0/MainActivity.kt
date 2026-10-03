package com.example.lab04variant0

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lab04variant0.ui.theme.Lab04Variant0Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab04Variant0Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LabScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

/**
 * Вычисление факториала n! через цикл for.
 * Пример: factorial(5) = 1 * 2 * 3 * 4 * 5 = 120.0
 * Возвращает Double, чтобы избежать переполнения при больших n.
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 2..n) {
        result *= i
    }
    return result
}

@Composable
fun LabScreen(modifier: Modifier = Modifier) {
    var xText by remember { mutableStateOf("") }
    var nText by remember { mutableStateOf("") }
    var resultText by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Лабораторная работа №4. Вариант 0",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "S = x/4! + x/5! + ... + x/n!",
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = xText,
            onValueChange = { xText = it },
            label = { Text("Число x") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = nText,
            onValueChange = { nText = it },
            label = { Text("Натуральное n (n >= 4)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {

            val x = xText.trim().toDoubleOrNull()
            val n = nText.trim().toIntOrNull()


            if (x == null) {
                resultText = "Ошибка: введите корректное число x"
            } else if (n == null || n < 4) {
                resultText = "Ошибка: n должно быть натуральным числом >= 4"
            } else {

                var sum = 0.0
                var lastTerm = 0.0
                var count = 0

                for (k in 4..n) {
                    lastTerm = x / factorial(k)
                    sum += lastTerm
                    count++
                }


                resultText = "Сумма S = $sum\n" +
                        "Последнее слагаемое = $lastTerm\n" +
                        "Количество итераций = $count"
            }
        }) {
            Text("OK")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = resultText,
            fontSize = 18.sp
        )
    }
}