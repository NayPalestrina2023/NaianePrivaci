package com.naiane.privaci.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.naiane.privaci.security.PasswordStore

@Composable
fun SetupScreen(store: PasswordStore, onDone: () -> Unit) {
    var first by remember { mutableStateOf("") }
    var second by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Criar senha privada", color = NeonGreen, fontSize = 26.sp)
        Spacer(Modifier.height(8.dp))
        Text(
            "Escolha uma senha numérica (mínimo 4 números). Depois, digite essa senha na calculadora e aperte = para entrar.",
            color = Color.White,
            fontSize = 14.sp
        )
        Spacer(Modifier.height(24.dp))

        OutlinedTextField(
            value = first,
            onValueChange = { first = it.filter(Char::isDigit) },
            label = { Text("Senha") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(
            value = second,
            onValueChange = { second = it.filter(Char::isDigit) },
            label = { Text("Repita a senha") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
            modifier = Modifier.fillMaxWidth()
        )

        if (error.isNotEmpty()) {
            Spacer(Modifier.height(8.dp))
            Text(error, color = Color(0xFFFF5252), fontSize = 14.sp)
        }

        Spacer(Modifier.height(24.dp))
        Button(
            onClick = {
                when {
                    first.length < 4 -> error = "A senha precisa ter pelo menos 4 números."
                    first != second -> error = "As senhas não são iguais."
                    else -> {
                        store.save(first)
                        onDone()
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = NeonGreen, contentColor = Color.Black),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Salvar senha")
        }
    }
}
