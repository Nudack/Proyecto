package com.example.hospedafcil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.example.hospedafcil.ui.theme.HospedaFácilTheme
import com.example.hospedafcil.ui.theme.login.ui.LoginScreen
import com.example.hospedafcil.ui.theme.login.viewModel.LoginViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HospedaFácilTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    //crear NavHost

                    var cambiarPantalla = rememberSaveable { mutableStateOf(true) }
                    if (cambiarPantalla.value){
                    LoginScreen(LoginViewModel(), continuar = {cambiarPantalla.value = false})
                    }else{

                    }
                }
            }
        }
    }
}
