package com.pablobarriosdevs.timercomposables

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import com.pablobarriosdevs.stopwatchorcountdown.rememberStopWatchOrCountdownState
import com.pablobarriosdevs.timercomposables.ui.theme.TimerComposablesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TimerComposablesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val sw = rememberStopWatchOrCountdownState(time = 5000, countDown = true)

                    Text(
                        text = sw.timeFormatted.value,
                    )

                    Button(
                        onClick = { sw.start() }
                    ) {
                        Text(text = "START")
                    }

                }
            }
        }
    }
}


/*@Composable
fun MainScreen() {


    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = sw.timeFormatted.value,
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(100.dp))

            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                Button(onClick = { sw.start() }) {
                    Text(text = "START")
                }
                Button(onClick = { sw.pause() }) {
                    Text(text = "PAUSE")
                }
                Button(onClick = { sw.reset() }) {
                    Text(text = "RESET")
                }


            }

        }

    }
}*/

