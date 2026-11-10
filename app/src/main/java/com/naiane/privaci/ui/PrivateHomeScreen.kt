package com.naiane.privaci.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.naiane.privaci.R

private val sections = listOf(
    "Aplicativos", "Fotos", "Vídeos", "Documentos", "Arquivos", "Configurações"
)

private val MarkGreen = Color(0xFF3DFF7F)
private val MarkBg = Color(0xFF060B09)
private val MarkGray = Color(0xFFA9B3AE)
private val MarkDim = Color(0xFF7E8A84)

@Composable
fun PrivateHomeScreen(onLock: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(20.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF0D0D0D))
                    .border(1.dp, NeonGreen, CircleShape)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize().scale(1.9f)
                )
            }
            Spacer(Modifier.width(14.dp))
            Column {
                Text("Naiane Ramos", color = NeonGreen, fontSize = 26.sp)
                Text("Analista e Desenvolvimento de Sistemas", color = Color.White, fontSize = 13.sp)
            }
        }
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

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MarkBg, RoundedCornerShape(20.dp))
                .border(1.5.dp, MarkGreen, RoundedCornerShape(20.dp))
                .padding(horizontal = 16.dp, vertical = 18.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("NR SYSTEMS®", color = MarkGreen, fontSize = 26.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
            Spacer(Modifier.height(10.dp))
            Text("Naiane Ramos da Silva", color = Color.White, fontSize = 17.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
            Spacer(Modifier.height(10.dp))
            Text("ANALISTA DE SISTEMAS", color = MarkGreen, fontSize = 13.sp, textAlign = TextAlign.Center)
            Spacer(Modifier.height(8.dp))
            Text("ANÁLISE E DESENVOLVIMENTO DE SISTEMAS", color = MarkGray, fontSize = 11.sp, textAlign = TextAlign.Center)
            Spacer(Modifier.height(8.dp))
            Text("© 2026 · Todos os direitos reservados.", color = MarkDim, fontSize = 11.sp, textAlign = TextAlign.Center)
        }

        Spacer(Modifier.height(16.dp))
        Button(
            onClick = onLock,
            colors = ButtonDefaults.buttonColors(containerColor = NeonGreen, contentColor = Color.Black),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Sair e voltar à calculadora")
        }
    }
}
