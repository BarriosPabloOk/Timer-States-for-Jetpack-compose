package com.pablobarriosdevs.stopwatchorcountdown

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
fun rememberStopWatchOrCountdownState(
    time:Long = 0L,
    pattern: Formatter = Formatter.MM_SS_SS,
    countDown: Boolean = false
) : StopWatch {
    return remember {
        StopWatch(
            time,
            pattern,
            countDown
        )
    }

}