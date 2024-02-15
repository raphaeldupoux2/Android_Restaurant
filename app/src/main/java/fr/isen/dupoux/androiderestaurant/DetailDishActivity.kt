package fr.isen.dupoux.androiderestaurant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.isen.dupoux.androiderestaurant.ui.theme.Android_RestaurantTheme

class DetailDishActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val DetailDishTitle = intent.getStringExtra("dishTitle") ?: ""  // ex: tartare de saumon

        setContent {
            Android_RestaurantTheme {
                // A surface container using the 'background' color from the theme
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    // Image en fond d'écran
                    Image(
                        painter = painterResource(id = R.drawable.fond_tableau_noir), // Remplacez "votre_image" par le nom de votre image sans extension
                        contentDescription = null, // Ajoutez une description si nécessaire
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        text = DetailDishTitle,
                        color = Color(0xFFFFA500),
                        style = TextStyle(
                            fontSize = 24.sp // Modifiez la taille de la police selon vos besoins
                        ),
                        modifier = Modifier
                            .align(Alignment.TopCenter) // Alignement vertical en haut
                            .offset(y = 5.dp) // Ajustez la valeur y pour déplacer le texte vers le bas
                    )
                    DetailDishListComponent(getDishDetail(DetailDishTitle))
                }
            }
        }
    }
    fun getDishDetail(category: String): List<String> {
        return when (category) {
            "Tartare saumon mariné et avocat" -> resources.getStringArray(R.array.TartareSaumonDétails).toList()
            "Salade de chevre chaud" -> resources.getStringArray(R.array.SaladeChevreDétails).toList()
            "Salade Tomate Mozarella et sauce Balsamique" -> resources.getStringArray(R.array.TomateMozzaDétails).toList()

            "Lasagne" -> resources.getStringArray(R.array.LasagneDétails).toList()
            "Blanquette de veau avec riz" -> resources.getStringArray(R.array.BlanquetteDétails).toList()
            "Pate Carbonara" -> resources.getStringArray(R.array.PateCarbonaraeDétails).toList()

            "Moelleux au chocolat" -> resources.getStringArray(R.array.MoelleuxChocolatDetails).toList()
            "Tarte au citron meringuée" -> resources.getStringArray(R.array.TarteCitronMeringueDetails).toList()
            "Salade de fruits de saison" -> resources.getStringArray(R.array.SaladeFruitDetails).toList()
            else -> emptyList()
        }
    }
}

@Composable
fun DetailDishListComponent(detailDishes: List<String>) {
    LazyColumn (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        items(detailDishes.size) {detail ->
            DetailDishRow(detail = detailDishes[detail])
        }
    }
}

@Composable
fun DetailDishRow(detail: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .padding(8.dp)
                .background(
                    Color(0xFFFFA500), // Orange background
                    shape = RoundedCornerShape(8.dp) // Rounded corners
                )
        ) {
            Text(
                text = detail,
                color = Color.Black, // White text color
                textAlign = TextAlign.Center, // Center text horizontally
                modifier = Modifier.padding(16.dp) // Add padding inside the box
            )
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
fun Greeting3(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    Android_RestaurantTheme {
        Greeting3("Android")
    }
}