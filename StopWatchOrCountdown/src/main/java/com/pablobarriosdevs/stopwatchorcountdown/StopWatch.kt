package com.pablobarriosdevs.stopwatchorcountdown

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import kotlinx.coroutines.*
import kotlin.IllegalArgumentException
import kotlin.math.abs


@Stable
class StopWatch(
    _millis: Long,
    private val timePattern :Formatter,
    private val countDown: Boolean) {

    //StopWatch attributes
    private var coroutine = CoroutineScope(Dispatchers.Main)

    var isRunning = false
        private set

    private var lastTimeStamp = 0L

    var timeInMillis = _millis
        private set

    private val baseTime = timeInMillis

    // states of stopWatch object
    var timeFormatted = mutableStateOf(
        Formatter.formatTime(timeInMillis, timePattern)
    )
        private set

    init {
        if (_millis < 0L) throw IllegalArgumentException("_millis cannot be less than zero")
        timeFormatted.value = Formatter.formatTime(timeInMillis, timePattern)
    }


    fun start() {
        if (isRunning) return

        coroutine.launch {
            isRunning = true

            while (isRunning) {
                lastTimeStamp = System.currentTimeMillis()
                delay(10L)

                timeInMillis = timeInMillis.incrementOrDecrement(
                    val1 = System.currentTimeMillis() - lastTimeStamp,
                    decrement = countDown
                )
                lastTimeStamp = System.currentTimeMillis()

                val pat = if (abs(timeInMillis) > 3600000)
                    Formatter.HH_MM_SS_SS
                    else timePattern

                if (timeInMillis < 0L) {
                    timeFormatted.value =
                        "- " + Formatter.formatTime(-timeInMillis, pat)
                }else{
                    timeFormatted.value = Formatter.formatTime(timeInMillis, pat)
                }
            }
        }
    }

    fun pause() {
        isRunning = false
    }

    fun reset() {
        coroutine.cancel()
        coroutine = CoroutineScope(Dispatchers.Main)
        isRunning = false
        lastTimeStamp = 0L
        timeInMillis = baseTime
        timeFormatted.value =  Formatter.formatTime(baseTime, timePattern)
    }

    private fun Long.incrementOrDecrement(val1:Long, decrement: Boolean): Long{
        var result = this
        return if (!decrement){
            result += val1
            result

        }else{
            result-= val1
            result
        }

    }


}