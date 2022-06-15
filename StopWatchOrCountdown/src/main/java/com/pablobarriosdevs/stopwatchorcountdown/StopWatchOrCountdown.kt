package com.pablobarriosdevs.stopwatchorcountdown

/*Copyright (C) 2022  Pablo Barrios

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU Affero General Public License as published
by the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Affero General Public License for more details.

You should have received a copy of the GNU Affero General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/

import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.*
import kotlin.IllegalArgumentException
import kotlin.math.abs


@Stable
class StopWatchOrCountdown(
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