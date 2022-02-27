package ru.adanil.weather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import ru.adanil.weather.navigation.SetupNavGraph
import ru.adanil.weather.ui.theme.WeatherTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Handle the splash screen transition.
        installSplashScreen()

        super.onCreate(savedInstanceState)
        setContent {
            WeatherTheme {
                Text("Heelo world")
            }
        }
    }
}

data class Message(val author: String, val body: String)


//@Composable
//fun MessageCard(msg: Message) {
//    Row(modifier = Modifier.padding(all = 8.dp)) {
//        Image(
//            painter = painterResource(R.drawable.profile_picture),
//            contentDescription = "Contact profile picture",
//            modifier = Modifier
//                .clip(CircleShape)
//                .size(55.dp)
//                .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
//        )
//
//        Spacer(modifier = Modifier.width(8.dp))
//
//
//        var isExpanded by remember { mutableStateOf(false) }
//        val surfaceColor: Color by animateColorAsState(
//            if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface,
//        )
//
//        Column(
//            modifier = Modifier.clickable { isExpanded = !isExpanded },
//        ) {
//            Text(
//                text = msg.author,
//                color = MaterialTheme.colors.secondaryVariant,
//                style = MaterialTheme.typography.subtitle2
//            )
//            Spacer(modifier = Modifier.height(5.dp))
//
//            Surface(
//                color = surfaceColor,
//                modifier = Modifier.animateContentSize()
//            ) {
//                Text(
//                    text = msg.body,
//                    style = MaterialTheme.typography.body2,
//                    modifier = Modifier.padding(all = 4.dp),
//                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
//                )
//            }
//        }
//    }
//}
//
//@Composable
//fun Conversation(messages: List<Message>) {
//    LazyColumn {
//        items(messages) { message ->
//            MessageCard(message)
//        }
//    }
//}
//
//@Preview(name = "Light Mode")
//@Preview(
//    uiMode = Configuration.UI_MODE_NIGHT_YES,
//    showBackground = true,
//    name = "Dark Mode"
//)
//@Composable
//fun PreviewConversation() {
//    WeatherTheme() {
//        Conversation(
//            listOf(
//                Message("Artyom", "Test message"),
//                Message("Artyom", "Hey, take a look at Jetpack Compose, it's great!"),
//                Message("Artyom", "Test message"),
//                Message("Artyom", "Hey, take a look at Jetpack Compose, it's great!"),
//                Message("Artyom", "Test message"),
//                Message("Artyom", "Hey, take a look at Jetpack Compose, it's great!"),
//                Message("Artyom", "Test message"),
//                Message("Artyom", "Hey, take a look at Jetpack Compose, it's great!"),
//                Message("Artyom", "Test message"),
//                Message("Artyom", "Hey, take a look at Jetpack Compose, it's great!"),
//                Message("Artyom", "Test message"),
//                Message("Artyom", "Hey, take a look at Jetpack Compose, it's great!"),
//                Message("Artyom", "Test message"),
//                Message("Artyom", "Hey, take a look at Jetpack Compose, it's great!"),
//            )
//        )
//    }
//}