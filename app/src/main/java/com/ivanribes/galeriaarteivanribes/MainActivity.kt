package com.ivanribes.galeriaarteivanribes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ivanribes.galeriaarteivanribes.ui.theme.GaleriaArteIvanRibesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GaleriaArteIvanRibesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        PantallaPrincipal()
                    }
                }
            }
        }
    }
}


@Composable
fun PantallaPrincipal() {

    val obras = listOf(
        Obra("Mona Lisa", "Leonardo da Vinci", "1503", R.drawable.mona_lisa),
        Obra("La noche estrellada", "Vincent van Gogh", "1889", R.drawable.noche_estrellada),
        Obra("El grito", "Edvard Munch", "1893", R.drawable.el_grito)
    )

    var indice by remember { mutableIntStateOf(0) }

    val obraActual = obras[indice]

    Column(modifier = Modifier.fillMaxSize().padding(20.dp)) {
        Image(painter = painterResource(obraActual.foto),
            contentDescription = obraActual.nombre,
            modifier = Modifier.fillMaxWidth().weight(6f),
            contentScale = ContentScale.Fit)
        Column(
            modifier = Modifier.fillMaxWidth().weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = obraActual.nombre,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp)
            Text(text = "${obraActual.artista} (${obraActual.año})")
        }
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp).weight(1f),
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = {indice = (indice - 1 + obras.size) % obras.size},
                Modifier.weight(1f)) {
                Text("ANTERIOR")
            }
            Button(onClick = {indice = (indice + 1) % obras.size},
                Modifier.weight(1f)) {
                Text("SIGUIENTE")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PantallaPrincipalPreview() {
    PantallaPrincipal()
}


data class Obra(val nombre: String,
                val artista: String,
                val año: String,
                @DrawableRes val foto: Int)

