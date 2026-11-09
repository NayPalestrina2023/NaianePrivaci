package com.naiane.privaci.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val sections = listOf(
    "Aplicativos", "Fotos", "Vídeos", "Documentos", "Arquivos", "Configurações"
)

@Composable
fun PrivateHomeScreen(onLock: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(20.dp)
    ) {
        Text("Naiane Ramos", color = NeonGreen, fontSize = 28.sp)
        Text("Analista e Desenvolvimento de Sistemas", color = Color.White, fontSize = 14.sp)
        Spacer(Modifier.height(24.dp))

        sections.chunked(2).forEach { pair ->
            Row(
                modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                pair.forEach { name ->
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .height(100.dp)
                            .border(1.dp, NeonGreen, RoundedCornerShape(16.dp))
                            .padding(12.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(name, color = Color.White, fontSize = 16.sp)
                        Text("em breve", color = NeonGreen, fontSize = 12.sp)
                    }
                }
            }
        }

        Spacer(Modifier.weight(1f))
        Button(
            onClick = onLock,
            colors = ButtonDefaults.buttonColors(containerColor = NeonGreen, contentColor = Color.Black),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Sair e voltar à calculadora")
        }
    }
}
