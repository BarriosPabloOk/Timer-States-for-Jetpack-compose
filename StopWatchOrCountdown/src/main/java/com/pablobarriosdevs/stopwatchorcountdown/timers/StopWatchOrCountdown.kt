package com.pablobarriosdevs.stopwatchorcountdown.timers

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

import android.util.Log
import androidx.compose.runtime.Stable
import com.pablobarriosdevs.stopwatchorcountdown.formatter.Formatter
import com.pablobarriosdevs.stopwatchorcountdown.formatter.Formatter.Companion.formatTime
import com.pablobarriosdevs.stopwatchorcountdown.formatter.Patterns
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.IllegalArgumentException
import kotlin.math.abs

/*This class implement a corutine to work with.
Is inaccurate.*/
@Stable
class StopWatchOrCountdown(
    _millis: Long,
    private val timePattern : Patterns,
    private val countDown: Boolean) {

    //StopWatch attributes
    private val formatter = Formatter()
    private var coroutine = CoroutineScope(Dispatchers.Main)

    var isRunning = false
        private set

    private var lastTimeStamp = 0L

    var timeInMillis = _millis
        private set

    private val baseTime = timeInMillis

    // states of stopWatch object
    var timeFormatted : MutableStateFlow<String>? = null
        private set

    init {
        if (_millis < 0L) throw IllegalArgumentException("_millis cannot be less than zero")
        timeFormatted = MutableStateFlow(formatTime(timeInMillis, timePattern))
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
                    Patterns.HH_MM_SS_SS
                    else timePattern

                if (timeInMillis < 0L) {
                    timeFormatted?.value =
                        "- " + formatTime(-timeInMillis, pat)
                }else{
                    timeFormatted?.value = formatTime(timeInMillis, pat)
                }

                Log.d("null", timeInMillis.toString())
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
        timeFormatted?.value =  formatTime(baseTime, timePattern)
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