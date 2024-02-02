package fr.isen.dupoux.androiderestaurant

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import fr.isen.dupoux.androiderestaurant.ui.theme.Android_RestaurantTheme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext


class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Android_RestaurantTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = Color.Black) {
                    // Greeting("Android")
                    HomeScreen(::onMenuClick)
                }
            }
        }
    }

    private fun onMenuClick(category: String) {
        Toast.makeText(this, category, Toast.LENGTH_LONG).show()
        val intent = Intent(this, CategoryActivity::class.java).apply {
            putExtra("categoryTitle", category)
        }
        startActivity(intent)
    }
}

@Composable
fun HomeScreen(onMenuClick: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp), // Ajustez le padding selon vos besoins
            horizontalArrangement = Arrangement.End
        ) {
            WelcomeText()

        }
        ImageDisplay()
        Spacer(modifier = Modifier.height(24.dp))

        ButtonWithText("Entrée", onMenuClick)
        ButtonWithText("Plat", onMenuClick)
        ButtonWithText("Dessert", onMenuClick)
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun ButtonWithText(buttonText: String, onMenuClick: (String) -> Unit) {
    val context = LocalContext.current

    Button(
        onClick = {
            showToast(context, "Vous avez cliqué sur $buttonText.")
            onMenuClick(buttonText)
        },
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0x00000000)) // Fond transparent
    ) {
        Text(
            text = buttonText,
            color = Color(0xFFFFA500), // Orange
        )
    }
}

@Composable
fun ImageDisplay() {
    Image(
        painter = painterResource(id = R.drawable.image_cuistot),
        contentDescription = null, // La description doit être fournie si l'image est importante pour l'accessibilité
        modifier = Modifier.size(300.dp) // Ajustez la taille de l'image selon vos besoins
    )
}

@Composable
fun WelcomeText() {
    Text(
        text = "Bienvenue \nchez \n\nDroid Restaurant",
        style = TextStyle(
            color = Color(0xFFFFA500), // Orange
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif,
            fontSize = 24.sp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Android_RestaurantTheme {
        Greeting("Android")
    }
}

fun showToast(context: android.content.Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}
