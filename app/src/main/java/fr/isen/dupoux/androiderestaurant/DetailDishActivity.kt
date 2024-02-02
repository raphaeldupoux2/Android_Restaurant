package fr.isen.dupoux.androiderestaurant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import fr.isen.dupoux.androiderestaurant.ui.theme.Android_RestaurantTheme

class DetailDishActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val DetailDishTitle = intent.getStringExtra("dishTitle") ?: ""

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
                    Greeting3("Android")
                    DetailDishListComponent(getDishDetail(DetailDishTitle))
                }
            }
        }
    }
    fun getDishDetail(category: String): List<String> {
        return when (category.lowercase()) {
            "tartaresaumon" -> resources.getStringArray(R.array.TartareSaumonDétails).toList()
            "saladechevre" -> resources.getStringArray(R.array.SaladeChevreDétails).toList()
            "dessert" -> resources.getStringArray(R.array.TomateMozzaDétails).toList()

            "tartaresaumon" -> resources.getStringArray(R.array.LasagneDétails).toList()
            "saladechevre" -> resources.getStringArray(R.array.BlanquetteDétails).toList()
            "dessert" -> resources.getStringArray(R.array.PateCarbonaraeDétails).toList()

            "tartaresaumon" -> resources.getStringArray(R.array.MoelleuxChocolatDetails).toList()
            "saladechevre" -> resources.getStringArray(R.array.TarteCitronMeringueDetails).toList()
            "dessert" -> resources.getStringArray(R.array.SaladeFruitDetails).toList()
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
    Text(
        text = detail,
        color = Color(0xFF000000), // Orange
    )
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