package com.pablobarriosdevs.stopwatchorcountdown.states

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import com.pablobarriosdevs.stopwatchorcountdown.formatter.Patterns
import com.pablobarriosdevs.stopwatchorcountdown.timers.StopWatch

@Composable
fun rememberStopWatch(
    time:Long = 0L,
    pattern : Patterns  = Patterns.MM_SS_SS
): MutableState<StopWatch> {
    return remember {
        mutableStateOf(StopWatch(time, pattern))
    }
}


