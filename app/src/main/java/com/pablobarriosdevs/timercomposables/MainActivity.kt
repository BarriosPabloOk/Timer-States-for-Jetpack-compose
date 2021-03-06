package com.pablobarriosdevs.timercomposables

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pablobarriosdevs.stopwatchorcountdown.formatter.Patterns
import com.pablobarriosdevs.stopwatchorcountdown.states.rememberStopWatch
import com.pablobarriosdevs.timercomposables.ui.theme.TimerComposablesTheme

class MainActivity : ComponentActivity() {
    private val viewModel : MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TimerComposablesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen(viewModel)
                }
            }
        }
    }
}


@Composable
fun MainScreen(viewModel: MainViewModel) {
    val context = LocalContext.current
    val sw = viewModel.sw


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
                text =sw.timeFormatted.collectAsState().value,
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
}

