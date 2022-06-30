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
import androidx.compose.runtime.Stable
import com.pablobarriosdevs.stopwatchorcountdown.formatter.Formatter
import com.pablobarriosdevs.stopwatchorcountdown.formatter.Formatter.Companion.formatTime
import com.pablobarriosdevs.stopwatchorcountdown.formatter.Patterns
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.*
import java.util.Timer

@Stable
open class StopWatch(
    millis: Long,
    private val pattern: Patterns
) : TimerActions {

    private var timer = Timer()
    var isRunning = false
        private set
    var timeInMillis = millis
        protected set
    val timeFormatted = MutableStateFlow(formatTime(millis, pattern))


    override fun start() {
        if (isRunning) return
        val task = mTimerTask {
            isRunning = true
            timeInMillis += 10

            timeFormatted.value = if (timeInMillis >= 3600000L) formatTime(
                timeInMillis,
                Patterns.HH_MM_SS_SS
            )
            else formatTime(timeInMillis, pattern)
        }
        timer.scheduleAtFixedRate(
            task, 0, 10
        )
    }

    override fun pause() {
        isRunning = false
        timer.cancel()
        timer = Timer()
    }

    override fun reset() {
        pause()
        timeInMillis = 0L
        timeFormatted.value = formatTime(0L, pattern)
    }

    private fun mTimerTask(block: () -> Unit): TimerTask {
        return object : TimerTask() {
            override fun run() {
                block()
            }
        }
    }
}


