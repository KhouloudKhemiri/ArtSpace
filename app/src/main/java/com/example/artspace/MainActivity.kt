// package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artspace.R
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ArtSpaceScreen()
                }
            }
        }
    }
}

data class Artwork(
    val imageResId: Int,
    val title: String,
    val artist: String
)

@Composable
fun ArtSpaceScreen() {
    val artworks = listOf(
        Artwork(R.drawable.artspace, "The Starry Night", "Vincent van Gogh (1889)"),
        Artwork(R.drawable.artspace2, "Girl with a Pearl Earring", "Johannes Vermeer (1665)"),
        Artwork(R.drawable.artspace3, "The Persistence of Memory", "Salvador Dalí (1931)"),
        Artwork(R.drawable.artspace4, "The Great Wave off Kanagawa", "Hokusai (1831)"),
        Artwork(R.drawable.artspace5, "Personal Creation", "Khouloud (2025)")
    )

    var currentIndex by remember { mutableStateOf(0) }
    val artwork = artworks[currentIndex]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Cadre autour de l'image
        Card(
            border = BorderStroke(2.dp, Color.Gray),
            shape = RoundedCornerShape(8.dp),
            elevation = 4.dp,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = artwork.imageResId),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Titre et artiste
        Text(text = artwork.title, style = MaterialTheme.typography.h6)
        Text(text = artwork.artist, style = MaterialTheme.typography.body1)

        Spacer(modifier = Modifier.height(24.dp))

        // Boutons navigation
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    if (currentIndex > 0) currentIndex--
                },
                enabled = currentIndex > 0
            ) {
                Text("Précédent")
            }

            Button(
                onClick = {
                    if (currentIndex < artworks.size - 1) currentIndex++
                },
                enabled = currentIndex < artworks.size - 1
            ) {
                Text("Suivant")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        ArtSpaceScreen()
    }
}
