package com.example.myapplication3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.navigation.compose.composable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

// rutas de navegacion
sealed class Pantalla(val ruta:String){
    object Robin : Pantalla("pantalla_robin")
    object Luffy : Pantalla("pantalla_luffy")
}

// metodos de navegacion NavController

    fun NavController.navegarALuffy(){
        this.navigate(Pantalla.Luffy.ruta)
    }
    fun NavController.volverAtras(){
        this.popBackStack()
    }

// Main
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppNavegacion()
        }
    }
}

// Grafo  de navegacion
@Composable
fun AppNavegacion(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Pantalla.Robin.ruta
    ) {
        composable(Pantalla.Robin.ruta){
            RobinScreen (
                onIrALuffyClick = {navController.navegarALuffy()}
            )
        }
        composable(Pantalla.Luffy.ruta){
            LuffyScreen (
                onVolverClick = {navController.volverAtras()}
            )
        }
    }

}

// Pantalla Robin
@Composable
fun RobinScreen(onIrALuffyClick: ()-> Unit){
    Column(
        Modifier.fillMaxSize(),
        Arrangement.Center,
        Alignment.CenterHorizontally,
    )  {
        Text("Hola mundo en android", fontSize = 20.sp)
        Text("Mi nombre es Nico Robin", fontSize = 26.sp)

        Image(
            painter = painterResource(id = R.drawable.nico_robin),
            contentDescription = "Nico Robin",
            modifier = Modifier.height(300.dp)
        )
        Button(onClick = { onIrALuffyClick() }) {
            Text("Ir a pantalla de Luffy")
        }
    }
}

//Pantalla Luffy
@Composable
fun LuffyScreen(onVolverClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Hola mundo en android", fontSize = 20.sp)
        Text("Mi nombre es Monkey D. Luffy", fontSize = 26.sp)

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(id = R.drawable.luffy),
            contentDescription = "Luffy",
            modifier = Modifier.height(300.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = { onVolverClick() }) {
            Text("Volver a Nico Robin")
        }
    }
}

