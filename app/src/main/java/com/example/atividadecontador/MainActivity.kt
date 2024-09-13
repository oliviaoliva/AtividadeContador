package com.example.atividadecontador

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.atividadecontador.ui.theme.AtividadeContadorTheme
import com.example.atividadecontador.ui.viewmodels.ContadorViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AtividadeContadorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column {
                        val viewModel by viewModels<ContadorViewModel>()
                        val contador by viewModel.contador.collectAsState()
                        ContadorStateless(
                            contador,
                            onIncrementContador = { viewModel.incrementar() },
                            onDecrementContador = { viewModel.decrementar() },
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ContadorStateless(
    contador: Int,
    onIncrementContador: () -> Unit,
    onDecrementContador: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Contador: $contador"
        )
        Button(onClick = onIncrementContador) {
            Text("Incremente aqui")
        }
        Button(onClick = onDecrementContador) {
            Text("Decremente aqui")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AtividadeContadorTheme {
        ContadorStateless(contador = 0, onIncrementContador = {}, onDecrementContador = {})
    }
}
