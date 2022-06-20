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
import android.os.CountDownTimer
import androidx.compose.runtime.Stable
import com.pablobarriosdevs.stopwatchorcountdown.formatter.Formatter
import com.pablobarriosdevs.stopwatchorcountdown.formatter.Formatter.Companion.formatTime
import com.pablobarriosdevs.stopwatchorcountdown.formatter.Patterns
import kotlinx.coroutines.flow.MutableStateFlow

@Stable
class Countdown(
    millis: Long,
    private val pattern: Patterns = Patterns.HH_MM_SS,
    private val finish: ()->Unit) :TimerActions{

    private  lateinit var timer :CountDownTimer
    private val base = millis
    val timeFormatted = MutableStateFlow(formatTime(millis, pattern))
    var timeInMillis = millis
        private set

    override fun start() {
        timer = object :CountDownTimer(timeInMillis, 10L){
            override fun onTick(p0: Long) {
                timeInMillis = p0
                timeFormatted.value = formatTime(timeInMillis, pattern)
            }

            override fun onFinish() {
                reset()
                finish()
            }
        }.start()
    }

    override fun pause() {
        timer.cancel()
    }

    override fun reset() {
        timer.cancel()
        timeInMillis = base
        timeFormatted.value = formatTime(timeInMillis, pattern)
    }





}