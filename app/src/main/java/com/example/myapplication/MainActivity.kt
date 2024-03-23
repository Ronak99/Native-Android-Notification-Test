package com.example.myapplication

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.Models.AppNotification
import com.example.myapplication.Services.NotificationService
import com.example.myapplication.ui.theme.MyApplicationTheme
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val appContext = this

        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Greeting("Android")
                        DispatchNotificationButton(appContext)
                        CancelNotificationButton(appContext)
                    }
                }
            }
        }
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
fun DispatchNotificationButton(context:Context) {
    Button(onClick = {
        // dispatch a notification
        dispatchNotification(context)
    }) {
        Text("Dispatch Notification")
    }
}

@Composable
fun CancelNotificationButton(context:Context) {
    Button(onClick = {
        // dispatch a notification
        cancelNotification(context)
    }) {
        Text("Cancel Notification")
    }
}


private val jsonBuilder = Json {
    ignoreUnknownKeys = true
}

fun dispatchNotification(context:Context) {
    val notificationService = NotificationService(context);

    val json = """
        {
            "type":"delivery",
            "article":{
                "id":"regular-string-id-2"
                "title": "News title",
                "content": "News Content",
                "image": "https://png.pngtree.com/thumb_back/fh260/background/20230519/pngtree-landscape-jpg-wallpapers-free-download-image_2573540.jpg",
                "published_on": "Date",
                "category_list": ["entertainment", "politics"],
                "relevancy": "all",
                "source_url": "https://www.google.com"
            }
        }
    """.trimIndent()

    val appNotification = jsonBuilder.decodeFromString<AppNotification>(json)
    notificationService.dispatchNotification(appNotification);
}


fun cancelNotification(context:Context) {
    val notificationService = NotificationService(context);

    val json = """
        {
            "type":"cancel",
            "article":{
                "id":"regular-string-id-2"
                "title": "News title",
                "content": "News Content",
                "image": "https://png.pngtree.com/thumb_back/fh260/background/20230519/pngtree-landscape-jpg-wallpapers-free-download-image_2573540.jpg",
                "published_on": "Date",
                "category_list": ["entertainment", "politics"],
                "relevancy": "all",
                "source_url": "https://www.google.com"
            }
        }
    """.trimIndent()

    val appNotification = jsonBuilder.decodeFromString<AppNotification>(json)
    notificationService.cancelNotification(appNotification);
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}