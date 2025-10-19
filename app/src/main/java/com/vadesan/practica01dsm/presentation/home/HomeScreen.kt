package com.vadesan.practica01dsm.presentation.home

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vadesan.practica01dsm.presentation.data.remote.firebase.FirebaseAuthManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavController){
    var amount by remember { mutableStateOf("") }
    var originCurrency by remember { mutableStateOf("") }
    var destinyCurrency by remember { mutableStateOf("") }
    var resultAmount by remember { mutableStateOf("") }

    val context = LocalContext.current

    Column (
        modifier = Modifier.padding(16.dp),
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Text("Conversor de Divisas", style = MaterialTheme.typography.titleLarge)

        OutlinedTextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Monto") },
            modifier = Modifier.padding(vertical = 8.dp).fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = originCurrency,
            onValueChange = { originCurrency = it },
            label = { Text("De") },
            modifier = Modifier.padding(vertical = 8.dp).fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
            }
        ) {
            Icon(Icons.Default.Refresh, contentDescription = "Invertir")
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = destinyCurrency,
            onValueChange = { destinyCurrency = it },
            label = { Text("A") },
            modifier = Modifier.padding(vertical = 8.dp).fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (amount.isNotBlank()
                    && originCurrency.isNotBlank()
                    && destinyCurrency.isNotBlank()
                ){
                    CoroutineScope(Dispatchers.Main).launch {
                        val result = FirebaseAuthManager.registerOperation(originCurrency, destinyCurrency, amount.toDouble(), 0.0)
                        if (!result.isSuccess) {
                            val error = result.exceptionOrNull()?.message?: "Error desconocido"
                            Toast.makeText(context
                                , error
                                , Toast.LENGTH_SHORT).show()
                        }
                    }

                    resultAmount = "$amount $originCurrency equivalen a  $destinyCurrency"
                }
            },
            modifier = Modifier.padding(vertical = 8.dp).fillMaxWidth()
        ) {
            Text("Convertir")
        }

        Spacer(modifier = Modifier.height(16.dp))

        resultAmount?.let {
            Text( resultAmount, style = MaterialTheme.typography.bodyLarge)
        }

    }
}