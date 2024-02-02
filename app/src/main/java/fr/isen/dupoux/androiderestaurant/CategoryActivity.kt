package fr.isen.dupoux.androiderestaurant

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.android.volley.Request
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
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
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DishListComponent(getCategoryItems(categoryTitle), ::onDetailDishClick)
                }
            }
        }
    }
    fun fetchdata() {
        val url = "http://test.api.catering.bluecodegames.com/menu"
        val jsonObject = JSONObject()
        jsonObject.put("id_shop", "1")
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.POST,
            url,
            jsonObject,
            {
                Log.d("CategoryActivity", "les données en brut : $it")
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
    Button(
        onClick = {
//            onDetailDishClick(dish)
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