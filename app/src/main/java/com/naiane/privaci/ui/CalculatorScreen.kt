package com.naiane.privaci.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.naiane.privaci.calculator.CalculatorEngine
import com.naiane.privaci.security.PasswordStore

private val operators = "+-×÷"

@Composable
fun CalculatorScreen(store: PasswordStore, onUnlock: () -> Unit) {
    var expr by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }
    var justEvaluated by remember { mutableStateOf(false) }

    fun press(key: String) {
        when (key) {
            "C" -> { expr = ""; result = ""; justEvaluated = false }
            "⌫" -> { expr = expr.dropLast(1); result = ""; justEvaluated = false }
            "=" -> {
                if (expr.isNotEmpty()) {
                    val r = CalculatorEngine.evaluate(expr)
                    if (r == null) {
                        result = "Erro"
                    } else {
                        result = r
                        expr = r
                        justEvaluated = true
                    }
                }
            }
            "+", "-", "×", "÷" -> {
                justEvaluated = false
                if (expr.isEmpty()) {
                    if (key == "-") expr = "-"
                } else if (expr.last() in operators) {
                    expr = expr.dropLast(1) + key
                } else {
                    expr += key
                }
            }
            else -> {
                if (justEvaluated && key != "%") {
                    expr = key
                    result = ""
                } else {
                    expr += key
                }
                justEvaluated = false

                // Entrada automática: se os 8 últimos números digitados forem a senha, abre sozinho
                if (key[0].isDigit()) {
                    val tail = expr.takeLast(8)
                    if (tail.length == 8 && tail.all { it.isDigit() } && store.verify(tail)) {
                        expr = ""
                        result = ""
                        onUnlock()
                    }
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Column(
            modifier = Modifier.weight(1f).fillMaxWidth(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.End
        ) {
            Text(expr, color = Color.White, fontSize = 40.sp, textAlign = TextAlign.End, maxLines = 2)
            Text(result, color = NeonGreen, fontSize = 28.sp, textAlign = TextAlign.End)
        }

        val rows = listOf(
            listOf("C", "⌫", "%", "÷"),
            listOf("7", "8", "9", "×"),
            listOf("4", "5", "6", "-"),
            listOf("1", "2", "3", "+")
        )
        rows.forEach { row ->
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                row.forEach { key -> CalcButton(key, 1f) { press(key) } }
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            CalcButton("0", 2f) { press("0") }
            CalcButton(".", 1f) { press(".") }
            CalcButton("=", 1f) { press("=") }
        }
    }
}

@Composable
private fun RowScope.CalcButton(label: String, weight: Float, onClick: () -> Unit) {
    val isEquals = label == "="
    val isOperator = label.length == 1 && label[0] in "+-×÷%" || label == "C" || label == "⌫"
    Box(
        modifier = Modifier
            .weight(weight)
            .height(72.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(if (isEquals) NeonGreen else DarkGray)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            fontSize = 26.sp,
            color = when {
                isEquals -> Color.Black
                isOperator -> NeonGreen
                else -> Color.White
            }
        )
    }
}
