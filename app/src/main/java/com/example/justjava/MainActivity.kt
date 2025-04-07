package com.example.justjava

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf // Import mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.justjava.ui.theme.JustJAVATheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JustJAVATheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = " Quantity",
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val quantity = remember { mutableIntStateOf(0) }
    val total = remember { mutableIntStateOf(0) }
    val context = LocalContext.current
    Image(
        painter = painterResource(id = R.drawable.coffe), // Replace with your actual image resource
        contentDescription = null,
        modifier = Modifier.fillMaxSize(), // Cover the entire screen
        contentScale = ContentScale.Crop // Ensure the image covers the entire area
    )
    Column(modifier = modifier) {
        Text(
            text = "$name!".uppercase(),
            color = Color.White,
            modifier = Modifier.padding(start = 16.dp),
        )

        Button(onClick = { quantity.value++ }, modifier = Modifier.padding(start = 16.dp)) {
            Text(text = "+")
        }
        Text(
            text = quantity.value.toString(),
            fontSize = 16.sp,
            color = Color.White,
            modifier = Modifier.testTag("quantity_text_view").padding(start = 40.dp),
        )
        Button(onClick = { if (quantity.value > 0) quantity.value-- }, modifier = Modifier.padding(start = 16.dp)) {
            Text(text = "-")
        }
        Text(
            text = "Price",
            fontSize = 25.sp,
            color = Color.White,
            modifier = Modifier.testTag("total_text_view").padding(start = 20.dp),
        )
        Text(
            text = "$" + total.value.toString(),
            fontSize = 25.sp,
            color = Color.White,
            modifier = Modifier.testTag("Price_text_view").padding(start = 20.dp).padding(16.dp),
        )
        Button(onClick = { call_total(quantity.value, total, context) }, modifier = Modifier.padding(start = 10.dp)) {
        //Button(onClick = { call(quantity.value, context) }, modifier = Modifier.padding(start = 10.dp)) {
            Text(text = "Order")
        }
        //Button(onClick = { call_total(quantity.value, total, context) }, modifier = Modifier.padding(start = 10.dp)) {
         //   Text(text = "Total")
        //}
    }
}

fun call_total(quantity: Int, total: androidx.compose.runtime.MutableState<Int>, context: android.content.Context) {
    val pricePerCoffee = 8
    total.value = quantity * pricePerCoffee
}

fun call(quantity: Int, context: android.content.Context) {
    val message = "I want to order $quantity coffees."
    val intent = Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("mailto:")
        putExtra(Intent.EXTRA_SUBJECT, "Coffee Order")
        putExtra(Intent.EXTRA_TEXT, message)
    }
    try {
        context.startActivity(intent)
    } catch (e: Exception) {
        Toast.makeText(context, "No email app found.", Toast.LENGTH_SHORT).show()
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JustJAVATheme {
        Greeting("Android")
    }
}