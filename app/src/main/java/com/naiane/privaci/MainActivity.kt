package com.naiane.privaci

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.naiane.privaci.security.PasswordStore
import com.naiane.privaci.ui.CalculatorScreen
import com.naiane.privaci.ui.PrivaciTheme
import com.naiane.privaci.ui.PrivateHomeScreen
import com.naiane.privaci.ui.SetupScreen

class MainActivity : ComponentActivity() {

    private var unlocked by mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val store = PasswordStore(applicationContext)

        setContent {
            PrivaciTheme {
                var configured by remember { mutableStateOf(store.isSet()) }
                when {
                    !configured -> SetupScreen(store) { configured = true }
                    unlocked -> PrivateHomeScreen { unlocked = false }
                    else -> CalculatorScreen(store) { unlocked = true }
                }
            }
        }
    }

    // Ao sair do app, o painel privado fecha sozinho
    override fun onStop() {
        super.onStop()
        unlocked = false
    }
}
