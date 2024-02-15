package fr.isen.dupoux.androiderestaurant

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.dupoux.androiderestaurant.ui.theme.Android_RestaurantTheme
import org.json.JSONObject


class CategoryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val categoryTitle = intent.getStringExtra("categoryTitle") ?: ""
        fetchdata()
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
                        text = categoryTitle,
                        color = Color(0xFFFFA500),
                        style = TextStyle(
                            fontSize = 24.sp // Modifiez la taille de la police selon vos besoins
                        ),
                        modifier = Modifier
                            .align(Alignment.TopCenter) // Alignement vertical en haut
                            .offset(y = 5.dp) // Ajustez la valeur y pour déplacer le texte vers le bas
                    )

                    DishListComponent(getCategoryItems(categoryTitle), ::onDetailDishClick)
                }
            }
        }
    }
    fun fetchdata() {  // categoryTitle: String, itemState: SnapshotStateList<RouteListingPreference.Item>
        val url = "http://test.api.catering.bluecodegames.com/menu"
        val jsonObject = JSONObject()
        jsonObject.put("id_shop", "1")
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.POST,
            url,
            jsonObject,
            {
                Log.d("CategoryActivity", "les données en brut : $it")
                val result = Gson().fromJson(it.toString(), DataResult::class.java)
//                val itemsFromCategory = result.data.find { it.nameFr == categoryTitle }?.items ?: emptyList()
//                ItemState.addAll(itemsFromCategory)
                result.data[0].items
            },
            {
                Log.e("CategoryActivity", "ERREUR : $it")
            })
        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(jsonObjectRequest)
    }
    fun getCategoryItems(category: String): List<String> {
        return when (category.lowercase()) {
            "entrée" -> resources.getStringArray(R.array.entrees).toList()
            "plat" -> resources.getStringArray(R.array.plats).toList()
            "dessert" -> resources.getStringArray(R.array.desserts).toList()
            else -> emptyList()
        }
    }
    private fun onDetailDishClick(dish: String) {
        Toast.makeText(this, dish, Toast.LENGTH_LONG).show()
        val intent = Intent(this, DetailDishActivity::class.java).apply {
            putExtra("dishTitle", dish)
        }
        startActivity(intent)
    }
}

@Composable
fun DishListComponent(dishes: List<String>, onDetailDishClick: (String) -> Unit) {
    LazyColumn (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center

    ) {
        items(dishes.size) {dish ->
            DishRow(dish = dishes[dish], onDetailDishClick)
        }
    }
}

@Composable
fun DishRow(dish: String, onDetailDishClick: (String) -> Unit) {
    val context = LocalContext.current

    Button(
        onClick = {
            showToast(context, "Vous avez cliqué sur $dish.")
            onDetailDishClick(dish)
        },
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0x00000000)) // Fond transparent
    ) {
        Text(
            text = dish,
            color = Color(0xFF000000), // Orange
        )
    }
}


@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    Android_RestaurantTheme {
        Greeting2("Android")
    }
}