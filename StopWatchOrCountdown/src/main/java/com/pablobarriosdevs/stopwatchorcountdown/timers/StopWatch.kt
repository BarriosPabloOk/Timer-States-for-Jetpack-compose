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
import com.pablobarriosdevs.stopwatchorcountdown.formatter.Patterns
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.*
import java.util.Timer

@Stable
open class StopWatch(millis: Long):TimerActions{
    private var timer =Timer()
    private var isRunning = false
    private val formatter = Formatter()
    private val base = millis
    var timeInMillis = millis
        protected set
    val stateFormat = MutableStateFlow(formatter.formatTime(millis, Patterns.MM_SS_SS))


    override fun start() {
        if(isRunning) return
        val task = mTimerTask {
            isRunning = true
            timeInMillis += 10

            stateFormat.value = if (timeInMillis < 3600000L) formatter.formatTime(
                timeInMillis,
                Patterns.MM_SS_SS
            )
            else formatter.formatTime(timeInMillis, Patterns.HH_MM_SS_SS)
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
        timeInMillis = base
        stateFormat.value = formatter.formatTime(base, Patterns.MM_SS_SS)
    }

    private fun mTimerTask(block:()->Unit): TimerTask {
        return object : TimerTask(){
            override fun run() {
                block()
            }
        }
    }
}


