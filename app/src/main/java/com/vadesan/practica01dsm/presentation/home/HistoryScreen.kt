package com.vadesan.practica01dsm.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vadesan.practica01dsm.presentation.data.model.OperationModel
import java.time.LocalDateTime

val mockOperations = listOf(
    OperationModel("USD", "EUR", 150.00, 350.00, LocalDateTime.now()),
    OperationModel("USD", "EUR", 150.00, 350.00, LocalDateTime.now()),
)

@Composable
fun HistoryScreen(navController: NavController){
    Column (
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Text("Historial de operaciones", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))



        LazyColumn{
            items(mockOperations){operation ->
                Card(
                    modifier = Modifier.fillMaxSize().padding(vertical = 8.dp)
                ){
                    Row(
                        modifier = Modifier.padding(12.dp)
                    ){
                        Text(operation.originCurrency, style = MaterialTheme.typography.titleMedium)
                        Text(operation.destinyCurrency, style = MaterialTheme.typography.titleMedium)
                        Text(operation.amount.toString(), style = MaterialTheme.typography.titleMedium)
                        Text(operation.result.toString(), style = MaterialTheme.typography.titleMedium)
                    }

                }
            }
        }

    }
}
